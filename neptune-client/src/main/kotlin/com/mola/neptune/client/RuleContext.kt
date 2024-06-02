package com.mola.neptune.client


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 13:05
 **/
class RuleContext {

    private var subRuleResult: MutableMap<String, Boolean> = mutableMapOf()

    private var subRuleLog: MutableList<String> = mutableListOf()

    fun addSubRuleResult(subRuleCode: String, result: Boolean) {
        subRuleResult[subRuleCode] = result
    }

    fun addSubRuleLog(log: String) {
        subRuleLog.add(log)
    }
}