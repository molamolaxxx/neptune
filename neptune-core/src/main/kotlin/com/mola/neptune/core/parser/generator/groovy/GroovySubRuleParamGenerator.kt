package com.mola.neptune.core.parser.generator.groovy

import com.mola.neptune.core.enums.DataTypeEnum
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
        val type = node.type
        val code = node.code

        if (DataTypeEnum.NUMBER.match(type)) {
            visitor.addTemp("String.valueOf($code)")
            return
        }
        if (DataTypeEnum.DATE.match(type)
            || DataTypeEnum.STRING.match(type)
            || DataTypeEnum.LIST.match(type)) {
            visitor.addTemp(code)
            return
        }
        throw RuntimeException("unknown param data type : $type")
    }
}