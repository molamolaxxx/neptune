package com.mola.neptune.core.parser.generator.groovy

import com.mola.neptune.core.parser.NeptuneRulePartVisitor
import com.mola.neptune.core.parser.generator.RuleGenerator
import com.mola.neptune.core.parser.node.SubRuleParam


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-09 23:43
 **/
class GroovySubRuleParamGenerator : RuleGenerator<SubRuleParam> {

    override fun generate(node: SubRuleParam, visitor: NeptuneRulePartVisitor) {
        visitor.addTemp("NeptuneMatchFunctions" +
                ".fetchAndParseParam(${node.code}, '${node.type}','${node.structure}')")

    }
}