package com.mola.neptune.core.generator.groovy

import com.mola.neptune.core.entity.SubRuleParam
import com.mola.neptune.core.enums.NeptuneDataTypeEnum
import com.mola.neptune.core.generator.RuleGenerator
import com.mola.neptune.core.parser.NeptuneRulePartVisitor


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-09 23:43
 **/
class GroovySubRuleParamGenerator : RuleGenerator<SubRuleParam> {

    override fun generate(node: SubRuleParam, visitor: NeptuneRulePartVisitor) {
        val type = node.type
        val code = node.code!!

        if (type == NeptuneDataTypeEnum.NUMBER.code) {
            visitor.addTemp("String.valueOf($code)")
            return
        }
        if (type == NeptuneDataTypeEnum.DATE.code
            || type == NeptuneDataTypeEnum.STRING.code ) {
            visitor.addTemp(code)
            return
        }
        throw RuntimeException("unknown param data type : $type")
    }
}