package com.mola.neptune.core.generator.groovy

import com.mola.neptune.core.entity.SubRuleValue
import com.mola.neptune.core.enums.NeptuneDataTypeEnum
import com.mola.neptune.core.generator.RuleGenerator
import com.mola.neptune.core.parser.NeptuneRulePartVisitor


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

        if (type == NeptuneDataTypeEnum.STRING.code ||
            type == NeptuneDataTypeEnum.NUMBER.code) {
            visitor.addTemp("'$value'")
            return
        }
        if (type == NeptuneDataTypeEnum.DATE.code) {
            visitor.addTemp("new Date($value)")
            return
        }
        if (type == NeptuneDataTypeEnum.DYNAMIC.code) {
            val dataSource = node.dataSource
            dataSource!!.accept(visitor)
            return
        }
        throw RuntimeException("unknown value data type : $type")
    }
}