package com.mola.neptune.core.parser.generator.groovy

import com.mola.neptune.core.enums.DataTypeEnum
import com.mola.neptune.core.parser.NeptuneRulePartVisitor
import com.mola.neptune.core.parser.generator.RuleGenerator
import com.mola.neptune.core.parser.node.SubRuleValue


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:44
 **/
class GroovySubRuleValueGenerator : RuleGenerator<SubRuleValue> {

    override fun generate(node: SubRuleValue, visitor: NeptuneRulePartVisitor) {
        val type = node.type
        val value = node.value

        if (DataTypeEnum.STRING.match(type) || DataTypeEnum.NUMBER.match(type)) {
            visitor.addTemp("'$value'")
            return
        }
        if (DataTypeEnum.DATE.match(type)) {
            visitor.addTemp("new Date($value)")
            return
        }
        if (DataTypeEnum.DYNAMIC.match(type)) {
            node.dataSource.accept(visitor)
            return
        }
        throw RuntimeException("unknown value data type : $type")
    }
}