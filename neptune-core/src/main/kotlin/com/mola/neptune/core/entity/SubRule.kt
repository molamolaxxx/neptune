package com.mola.neptune.core.entity

import com.mola.neptune.core.enums.NeptuneMatchMethodEnum
import com.mola.neptune.core.parser.NeptuneRulePartVisitor
import com.mola.neptune.core.parser.RuleParts


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:41
 **/
class SubRule: RuleParts {

    lateinit var subRuleName: String

    lateinit var subRuleCode: String

    lateinit var param: SubRuleParam

    lateinit var matchMethod: String

    lateinit var value: List<SubRuleValue>

    override fun accept(visitor: NeptuneRulePartVisitor) {
        visitor.addLine("// $subRuleName")
        visitor.add("def $subRuleCode = ")

        // 解析param
        param.accept(visitor)
        val paramTemp: String = visitor.getTemp()

        // 解析value
        val paramAndValue: MutableList<String> = mutableListOf()
        for (ruleValue in value) {
            ruleValue.accept(visitor)
            val valueTemp: String = visitor.getTemp()
            paramAndValue.add(getMatchMethodPattern(matchMethod, paramTemp, valueTemp, subRuleCode))
        }
        if (matchAll()) {
            visitor.add("(${paramAndValue.joinToString(" &&\n ")})")
        } else {
            visitor.add("(${paramAndValue.joinToString(" ||\n ")})")
        }

        visitor.newLine()
        visitor.addLine("ctx.addSubRuleResult('$subRuleCode', $subRuleCode)")
    }

    private fun matchAll(): Boolean {
        // 解析matchMethod
        val split = matchMethod.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        var matchAll = true
        if (split.size == 2 && split[1] == "any") {
            matchAll = false
        }
        return matchAll
    }

    private fun getMatchMethodPattern(
        matchMethod: String, paramTemp: String, valueTemp: String, subRuleCode: String
    ): String {
        // 解析matchMethod
        val split = matchMethod.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val matchMethodCode = split[0]
        val methodEnum: NeptuneMatchMethodEnum = NeptuneMatchMethodEnum.getByCode(matchMethodCode)
            ?: throw RuntimeException("unknown match method: $matchMethodCode")

        return "NeptuneMatchFunctions.${methodEnum.code}($paramTemp, $valueTemp, ctx, '$subRuleCode')"
    }
}