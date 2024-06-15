package com.mola.neptune.core.parser.generator.groovy

import com.mola.neptune.core.enums.MatchMethodEnum
import com.mola.neptune.core.parser.generator.base.SubRuleGenerator
import com.mola.neptune.core.parser.NeptuneRulePartVisitor
import com.mola.neptune.core.parser.node.SubRule


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
        val methodEnum: MatchMethodEnum = MatchMethodEnum.getByCode(matchMethod)
            ?: throw RuntimeException("unknown match method: $matchMethod")

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