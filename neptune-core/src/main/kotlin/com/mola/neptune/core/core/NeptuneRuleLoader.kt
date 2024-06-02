package com.mola.neptune.core.core

import com.mola.neptune.core.entity.RuleConfig
import com.mola.neptune.core.parser.NeptuneRuleParser

/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:18
 **/
object NeptuneRuleLoader {

    private val ruleScriptMap: MutableMap<String, String> = mutableMapOf()

    fun loadRule(ruleName: String, ruleDsl: String) {
        val ruleParser = NeptuneRuleParser()
        val ruleConfig: RuleConfig = ruleParser.parseConfig(ruleDsl)
        val script = ruleParser.transfer2Groovy(ruleConfig)
        println("ruleName: $ruleName, loadScript:")
        println(script)
        ruleScriptMap[ruleName] = script
    }

    fun getScript(ruleName: String): String? {
        return ruleScriptMap[ruleName]
    }
}