package com.mola.neptune.core.generator.groovy

import com.mola.neptune.core.entity.RuleAction
import com.mola.neptune.core.generator.RuleGenerator
import com.mola.neptune.core.parser.NeptuneRulePartVisitor


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-09 23:43
 **/
class GroovyRuleActionGenerator : RuleGenerator<RuleAction> {

    override fun generate(node: RuleAction, visitor: NeptuneRulePartVisitor) {
        if (node.type == "return") {
            visitor.addLine("\treturn new NeptuneResult('${node.content}', ctx)")
        }
    }
}