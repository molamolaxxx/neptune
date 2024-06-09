package com.mola.neptune.core.entity

import com.mola.neptune.core.parser.RuleNode


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:44
 **/
class SubRuleValue : RuleNode() {

    var type: String? = null

    var value: String? = null

    var dataSource: RuleDataSource? = null
}