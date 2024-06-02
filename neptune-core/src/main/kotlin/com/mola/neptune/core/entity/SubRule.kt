package com.mola.neptune.core.entity

import com.mola.neptune.core.parser.NeptuneRulePartVisitor
import com.mola.neptune.core.parser.RuleParts


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:41
 **/
class SubRule: RuleParts {

    private val directMatchMethods: Set<String> = setOf(
        ">", "<", ">=", "<=", "=="
    )

    lateinit var subRuleName: String

    lateinit var subRuleCode: String

    lateinit var param: SubRuleParam

    lateinit var matchMethod: String

    lateinit var value: List<SubRuleValue>

    override fun accept(ruleVisitor: NeptuneRulePartVisitor) {
        ruleVisitor.addLine("// $subRuleName")
        ruleVisitor.add("def $subRuleCode = ")

        // 解析param
        param.accept(ruleVisitor)
        val paramTemp: String = ruleVisitor.getTemp()

        // 解析value
        val paramAndValue: MutableList<String> = mutableListOf()
        for (ruleValue in value) {
            ruleValue.accept(ruleVisitor)
            val valueTemp: String = ruleVisitor.getTemp()
            paramAndValue.add(getMatchMethodPattern(matchMethod, paramTemp, valueTemp, subRuleCode))
        }
        if (matchAll()) {
            ruleVisitor.add("(${paramAndValue.joinToString(" &&\n ")})")
        } else {
            ruleVisitor.add("(${paramAndValue.joinToString(" ||\n ")})")
        }

        ruleVisitor.newLine()
        ruleVisitor.addLine("ctx.addSubRuleResult('$subRuleCode', $subRuleCode)")
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
        if (directMatchMethods.contains(matchMethodCode)) {
            return "$paramTemp $matchMethodCode $valueTemp"
        }
        when(matchMethodCode) {
            "after" ->
                return "NeptuneFunctions.leftAfterRight($paramTemp, $valueTemp, ctx, '$subRuleCode')"
            "contains" ->
                return "NeptuneFunctions.leftContainsRight($paramTemp, $valueTemp, ctx, '$subRuleCode')"
        }
        throw RuntimeException("unknown match method: $matchMethodCode")
    }
}