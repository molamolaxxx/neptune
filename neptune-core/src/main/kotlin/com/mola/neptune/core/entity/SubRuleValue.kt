package com.mola.neptune.core.entity

import com.mola.neptune.core.enums.NeptuneDataTypeEnum
import com.mola.neptune.core.parser.NeptuneRulePartVisitor
import com.mola.neptune.core.parser.RuleParts


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:44
 **/
class SubRuleValue : RuleParts{

    lateinit var type: String

    lateinit var value: String

    lateinit var dataSource: RuleDataSource

    override fun accept(visitor: NeptuneRulePartVisitor) {
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
            dataSource.accept(visitor)
            return
        }
        throw RuntimeException("unknown value data type : $type")
    }
}