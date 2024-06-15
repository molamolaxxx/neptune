package com.mola.neptune.core.parser.generator.groovy

import com.mola.neptune.core.parser.generator.RuleGenerator
import com.mola.neptune.core.parser.NeptuneRulePartVisitor
import com.mola.neptune.core.parser.node.RuleCondition


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-09 23:43
 **/
class GroovyRuleConditionGenerator : RuleGenerator<RuleCondition> {

    override fun generate(node: RuleCondition, visitor: NeptuneRulePartVisitor) {
        visitor.addLine("// ${node.conditionName}")
        visitor.add("if (${node.expression}) {")
        visitor.newLine()
        visitor.newTab()
        node.action.accept(visitor)
        visitor.add("}")
        visitor.newLine()
    }
}