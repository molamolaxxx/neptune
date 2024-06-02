package com.mola.neptune.core.entity

import com.mola.neptune.core.enums.NeptuneDataTypeEnum
import com.mola.neptune.core.parser.NeptuneRulePartVisitor
import com.mola.neptune.core.parser.RuleParts


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:43
 **/
class SubRuleParam: RuleParts {

    lateinit var type: String

    lateinit var code: String

    override fun accept(visitor: NeptuneRulePartVisitor) {
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