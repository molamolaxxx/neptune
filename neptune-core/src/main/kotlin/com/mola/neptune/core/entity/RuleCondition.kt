package com.mola.neptune.core.entity

import com.mola.neptune.core.parser.NeptuneRulePartVisitor
import com.mola.neptune.core.parser.RuleParts


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:39
 **/
class RuleCondition: RuleParts {

    lateinit var conditionName: String

    lateinit var expression: String

    lateinit var action: RuleAction

    override fun accept(visitor: NeptuneRulePartVisitor) {
        visitor.addLine("// $conditionName")
        visitor.add("if ($expression) {")
        visitor.newLine()
        action.accept(visitor)
        visitor.add("}")
        visitor.newLine()
    }
}