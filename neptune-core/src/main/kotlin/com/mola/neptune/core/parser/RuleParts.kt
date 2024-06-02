package com.mola.neptune.core.parser


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:33
 **/
interface RuleParts {

    /**
     * 接受访问
     */
    fun accept(visitor: NeptuneRulePartVisitor)
}