package com.mola.neptune.core.parser.generator.groovy

import com.mola.neptune.core.parser.node.RuleAction
import com.mola.neptune.core.parser.generator.RuleGenerator
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