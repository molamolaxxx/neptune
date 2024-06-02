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

    override fun accept(ruleVisitor: NeptuneRulePartVisitor) {
        // 导入
        ruleVisitor.addLine("// rule engine :$ruleName")
        ruleVisitor.addLine("import com.mola.neptune.client.NeptuneResult")
        ruleVisitor.addLine("import com.mola.neptune.core.function.NeptuneFunctions")
        ruleVisitor.addLine("import com.mola.neptune.client.RuleContext")
        ruleVisitor.newLine()

        ruleVisitor.addLine("// 初始化上下文")
        ruleVisitor.addLine("def ctx = new RuleContext()")
        ruleVisitor.newLine()
        for (subRule in subRules!!) {
            subRule.accept(ruleVisitor)
        }
        ruleVisitor.newLine()
        for (condition in conditions!!) {
            condition.accept(ruleVisitor)
        }
        ruleVisitor.newLine()
        ruleVisitor.addLine("return new NeptuneResult(ctx)")
    }

}