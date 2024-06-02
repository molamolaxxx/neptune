package com.mola.neptune.core.entity

import com.mola.neptune.core.parser.NeptuneRulePartVisitor
import com.mola.neptune.core.parser.RuleParts
import sun.security.ec.point.ProjectivePoint.Mutable
import kotlin.streams.toList


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:35
 **/
class RuleConfig : RuleParts{

    lateinit var ruleName: String

    lateinit var subRules: List<SubRule>

    lateinit var conditions: List<RuleCondition>

    override fun accept(visitor: NeptuneRulePartVisitor) {
        // 导入
        visitor.addLine("// rule engine :$ruleName")
        visitor.addLine("import com.mola.neptune.client.NeptuneResult")
        visitor.addLine("import com.mola.neptune.core.function.NeptuneMatchFunctions")
        visitor.addLine("import com.mola.neptune.core.function.NeptuneDataSourceFunctions")
        visitor.addLine("import com.mola.neptune.client.RuleContext")
        visitor.newLine()

        visitor.addLine("// 初始化上下文")
        visitor.addLine("def ctx = new RuleContext()")
        visitor.newLine()

        // 编译优化：条件匹配表达式
        val conditionWithSubRule: MutableMap<RuleCondition, MutableList<SubRule>> = mutableMapOf()
        for (condition in conditions) {
            if (conditionWithSubRule[condition] == null) {
                conditionWithSubRule[condition] = mutableListOf()
            }
            // 找到这个条件对应的表达式
            conditionWithSubRule[condition]!!.addAll(
                subRules.stream().filter { subRule ->
                    condition.expression.contains(subRule.subRuleCode)
                }.toList()
            )
        }

        val alreadyParsedSubCondition: MutableSet<String> = mutableSetOf()
        for (condition in conditions) {
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
        visitor.addLine("return new NeptuneResult(ctx)")
    }
}