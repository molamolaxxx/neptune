package com.mola.neptune.core.parser.node


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:41
 **/
class SubRule: RuleNode() {

    var subRuleName: String? = null

    var subRuleCode: String? = null

    var param: SubRuleParam? = null

    var matchMethod: String? = null

    var value: List<SubRuleValue>? = null
}