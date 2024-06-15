package com.mola.neptune.core.parser.generator.base

import com.mola.neptune.core.parser.generator.RuleGenerator
import com.mola.neptune.core.parser.NeptuneRulePartVisitor
import com.mola.neptune.core.parser.node.RuleCondition
import com.mola.neptune.core.parser.node.RuleConfig
import com.mola.neptune.core.parser.node.SubRule
import kotlin.streams.toList


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-03 18:59
 **/
abstract class RuleConfigGenerator : RuleGenerator<RuleConfig> {

    /**
     * 接受visitor的访问，生成target代码
     * @param node 规则节点
     * @param visitor 规则访问器
     */
    override fun generate(node: RuleConfig, visitor: NeptuneRulePartVisitor) {
        // 规则描述
        generateDescription(node, visitor)
        // 导入依赖
        generateImportDependency(node, visitor)
        // 初始化上下文
        generateContext(node, visitor)
        // 编排子规则和条件
        generateSubRuleAndCondition(node, visitor)
        // 返回默认结果
        generateDefaultAction(node, visitor)
    }

    /**
     * 生成规则描述
     * @param node 规则节点
     * @param visitor 规则访问器
     */
    protected abstract fun generateDescription(node: RuleConfig, visitor: NeptuneRulePartVisitor)

    /**
     * 生成规则依赖导入
     * @param node 规则节点
     * @param visitor 规则访问器
     */
    protected abstract fun generateImportDependency(node: RuleConfig, visitor: NeptuneRulePartVisitor)

    /**
     * 生成规则上下文
     * @param node 规则节点
     * @param visitor 规则访问器
     */
    protected abstract fun generateContext(node: RuleConfig, visitor: NeptuneRulePartVisitor)

    /**
     * 生成子规则和表达式
     * @param node 规则节点
     * @param visitor 规则访问器
     */
    private fun generateSubRuleAndCondition(node: RuleConfig, visitor: NeptuneRulePartVisitor) {
        val conditionWithSubRule: MutableMap<RuleCondition, MutableList<SubRule>> = mutableMapOf()
        for (condition in node.conditions) {
            if (conditionWithSubRule[condition] == null) {
                conditionWithSubRule[condition] = mutableListOf()
            }
            // 找到这个条件对应的表达式
            conditionWithSubRule[condition]!!.addAll(
                node.subRules.stream().filter { subRule ->
                    condition.expression.contains(subRule.subRuleCode)
                }.toList()
            )
        }

        val alreadyParsedSubCondition: MutableSet<String> = mutableSetOf()
        for (condition in node.conditions) {
            for (subRule in conditionWithSubRule[condition]!!) {
                if (alreadyParsedSubCondition.contains(subRule.subRuleCode)) {
                    continue
                }
                subRule.accept(visitor)
                alreadyParsedSubCondition.add(subRule.subRuleCode)
            }
            visitor.newLine()
            condition.accept(visitor)
        }
        visitor.newLine()
    }

    protected abstract fun generateDefaultAction(node: RuleConfig, visitor: NeptuneRulePartVisitor)
}