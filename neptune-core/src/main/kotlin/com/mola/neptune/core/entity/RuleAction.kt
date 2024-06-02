package com.mola.neptune.core.entity

import com.mola.neptune.core.parser.NeptuneRulePartVisitor
import com.mola.neptune.core.parser.RuleParts


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:36
 **/
class RuleAction: RuleParts {

    lateinit var type: String

    lateinit var content: String

    lateinit var method: String

    override fun accept(visitor: NeptuneRulePartVisitor) {
        if (type == "return") {
            visitor.addLine("\treturn new NeptuneResult('${content}', ctx)")
        }
    }
}