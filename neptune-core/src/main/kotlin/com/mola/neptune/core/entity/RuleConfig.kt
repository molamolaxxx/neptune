package com.mola.neptune.core.entity

import com.mola.neptune.core.parser.RuleNode


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:35
 **/
class RuleConfig : RuleNode() {

    var ruleName: String? = null

    var subRules: List<SubRule>? = null

    var conditions: List<RuleCondition>? = null
}