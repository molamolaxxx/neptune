package com.mola.neptune.core.parser.generator.base

import com.mola.neptune.core.parser.node.SubRule
import com.mola.neptune.core.parser.generator.RuleGenerator
import com.mola.neptune.core.parser.NeptuneRulePartVisitor


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-09 23:43
 **/
abstract class SubRuleGenerator : RuleGenerator<SubRule> {

    override fun generate(node: SubRule, visitor: NeptuneRulePartVisitor) {
        // 声明子规则
        generateSubRuleDescription(node, visitor)

        // 解析param
        node.param!!.accept(visitor)
        val paramTemp: String = visitor.getTemp()

        // 解析value
        val paramAndValues: MutableList<String> = mutableListOf()
        for (ruleValue in node.value!!) {
            ruleValue.accept(visitor)
            val valueTemp: String = visitor.getTemp()
            // 连接表达式入参与值，生成pv
            paramAndValues.add(
                connectParamAndValue(
                    node.matchMethod!!,
                    paramTemp, valueTemp,
                    node.subRuleCode!!
                )
            )
        }

        // 连接所有pv
        connectAllParamAndValues(node, visitor, paramAndValues)
    }

    protected fun matchAll(node: SubRule): Boolean {
        // 解析matchMethod
        val split = node.matchMethod!!.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        var matchAll = true
        if (split.size == 2 && split[1] == "any") {
            matchAll = false
        }
        return matchAll
    }

    abstract fun connectParamAndValue(
        matchMethod: String, paramTemp: String, valueTemp: String, subRuleCode: String
    ): String

    abstract fun connectAllParamAndValues(node: SubRule, visitor: NeptuneRulePartVisitor,
                                          paramAndValues: MutableList<String>)

    abstract fun generateSubRuleDescription(node: SubRule, visitor: NeptuneRulePartVisitor)
}