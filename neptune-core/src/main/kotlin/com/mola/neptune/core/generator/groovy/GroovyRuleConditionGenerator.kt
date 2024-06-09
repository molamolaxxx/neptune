package com.mola.neptune.core.generator.groovy

import com.mola.neptune.core.entity.RuleCondition
import com.mola.neptune.core.generator.RuleGenerator
import com.mola.neptune.core.parser.NeptuneRulePartVisitor


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
        node.action!!.accept(visitor)
        visitor.add("}")
        visitor.newLine()
    }
}