package com.mola.neptune.client


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:19
 **/
class NeptuneResult {

    var content: String? = null

    var context: RuleContext

    constructor(content: String, context: RuleContext) {
        this.content = content
        this.context = context
    }

    constructor(context: RuleContext) {
        this.context = context
    }
}