package com.mola.neptune.core.entity

import com.mola.neptune.core.parser.NeptuneRulePartVisitor
import com.mola.neptune.core.parser.RuleParts


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
        visitor.addLine("import com.mola.neptune.core.function.NeptuneFunctions")
        visitor.addLine("import com.mola.neptune.client.RuleContext")
        visitor.newLine()

        visitor.addLine("// 初始化上下文")
        visitor.addLine("def ctx = new RuleContext()")
        visitor.newLine()
        for (subRule in subRules) {
            subRule.accept(visitor)
        }
        visitor.newLine()
        for (condition in conditions) {
            condition.accept(visitor)
        }
        visitor.newLine()
        visitor.addLine("return new NeptuneResult(ctx)")
    }
}