package com.mola.neptune.core.parser.generator.base

import com.mola.neptune.core.core.NeptuneMatchMethodManager
import com.mola.neptune.core.parser.generator.RuleGenerator
import com.mola.neptune.core.parser.NeptuneRulePartVisitor
import com.mola.neptune.core.parser.node.SubRule


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-09 23:43
 **/
abstract class SubRuleGenerator : RuleGenerator<SubRule> {

    override fun generate(node: SubRule, visitor: NeptuneRulePartVisitor) {
        // 校验子规则合法
        validate(node)

        // 声明子规则
        visitor.newLine()
        generateSubRuleDescription(node, visitor)

        // 解析param
        node.param.accept(visitor)
        val paramTemp: String = visitor.getTemp()

        // 解析value
        node.value.accept(visitor)
        val valueTemp: String = visitor.getTemp()
        // 连接表达式入参与值，生成pv
        visitor.add(
            connectParamAndValue(
                node.matchMethod,
                paramTemp, valueTemp,
                node.subRuleCode
            )
        )
    }

    private fun validate(node: SubRule) {
        val param = node.param
        val value = node.value
        if (!NeptuneMatchMethodManager.match(
                param.type, node.matchMethod, value.type, param.structure, value.structure)) {
            throw RuntimeException("subRule `${node.subRuleName}` invalid, " +
                    "method `${node.matchMethod}` not match, given left = ${node.param.type} right = ${node.value.type}, " +
                    "expect = ${NeptuneMatchMethodManager.find(null, node.matchMethod, null)}")
        }
    }

    abstract fun connectParamAndValue(
        matchMethod: String, paramTemp: String, valueTemp: String, subRuleCode: String
    ): String

    abstract fun generateSubRuleDescription(node: SubRule, visitor: NeptuneRulePartVisitor)
}