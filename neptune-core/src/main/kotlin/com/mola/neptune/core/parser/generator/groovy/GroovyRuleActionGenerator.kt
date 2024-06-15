package com.mola.neptune.core.parser.generator.groovy

import com.mola.neptune.core.enums.ActionTypeEnum
import com.mola.neptune.core.parser.generator.RuleGenerator
import com.mola.neptune.core.parser.NeptuneRulePartVisitor
import com.mola.neptune.core.parser.node.RuleAction


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-09 23:43
 **/
class GroovyRuleActionGenerator : RuleGenerator<RuleAction> {

    override fun generate(node: RuleAction, visitor: NeptuneRulePartVisitor) {
        if (ActionTypeEnum.RETURN.match(node.type)) {
            visitor.addLine("return new NeptuneResult('${node.content}', ctx)")
        }
    }
}