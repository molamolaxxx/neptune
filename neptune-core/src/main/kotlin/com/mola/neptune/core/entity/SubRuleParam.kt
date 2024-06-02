package com.mola.neptune.core.entity

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
        visitor.addTemp(code)
    }
}