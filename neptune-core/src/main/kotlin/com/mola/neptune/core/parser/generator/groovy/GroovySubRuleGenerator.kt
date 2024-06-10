package com.mola.neptune.core.parser.generator.groovy

import com.mola.neptune.core.parser.node.SubRule
import com.mola.neptune.core.enums.NeptuneMatchMethodEnum
import com.mola.neptune.core.parser.generator.base.SubRuleGenerator
import com.mola.neptune.core.parser.NeptuneRulePartVisitor


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-09 23:43
 **/
class GroovySubRuleGenerator : SubRuleGenerator() {

    override fun generateSubRuleDescription(node: SubRule, visitor: NeptuneRulePartVisitor) {
        val subRuleName = node.subRuleName
        val subRuleCode = node.subRuleCode
        // 声明子规则
        visitor.addLine("// $subRuleName")
        visitor.add("def $subRuleCode = ")
    }

    override fun connectParamAndValue(
        matchMethod: String, paramTemp: String, valueTemp: String, subRuleCode: String
    ): String {
        // 解析matchMethod
        val split = matchMethod.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val matchMethodCode = split[0]
        val methodEnum: NeptuneMatchMethodEnum = NeptuneMatchMethodEnum.getByCode(matchMethodCode)
            ?: throw RuntimeException("unknown match method: $matchMethodCode")

        return "NeptuneMatchFunctions.${methodEnum.code}($paramTemp, $valueTemp, ctx, '$subRuleCode')"
    }

    override fun connectAllParamAndValues(node: SubRule, visitor: NeptuneRulePartVisitor,
                                          paramAndValues: MutableList<String>) {
        if (matchAll(node)) {
            visitor.add("(${paramAndValues.joinToString(" &&\n ")})")
        } else {
            visitor.add("(${paramAndValues.joinToString(" ||\n ")})")
        }
    }
}