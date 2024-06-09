package com.mola.neptune.core.entity

import com.mola.neptune.core.parser.RuleNode


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:36
 **/
class RuleAction: RuleNode() {

    var type: String? = null

    var content: String? = null

    var method: String? = null
}