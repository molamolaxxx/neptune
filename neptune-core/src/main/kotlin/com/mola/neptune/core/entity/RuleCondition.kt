package com.mola.neptune.core.entity

import com.mola.neptune.core.parser.RuleNode


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:39
 **/
class RuleCondition: RuleNode() {

    var conditionName: String? = null

    var expression: String? = null

    var action: RuleAction? = null
}