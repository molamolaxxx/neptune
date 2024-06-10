package com.mola.neptune.core.parser.node


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:45
 **/
class RuleDataSource: RuleNode() {

    var type: String? = null

    var db: String? = null

    var tb: String? = null

    var col: String? = null

    var where: List<String>? = null
}