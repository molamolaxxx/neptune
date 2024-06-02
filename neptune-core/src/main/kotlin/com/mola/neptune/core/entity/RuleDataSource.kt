package com.mola.neptune.core.entity

import com.mola.neptune.core.parser.NeptuneRulePartVisitor
import com.mola.neptune.core.parser.RuleParts


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:45
 **/
class RuleDataSource: RuleParts {

    lateinit var type: String

    lateinit var db: String

    lateinit var tb: String

    lateinit var where: List<String>

    override fun accept(visitor: NeptuneRulePartVisitor) {
        TODO("Not yet implemented")
    }
}