package com.mola.neptune.core.core

import com.mola.neptune.core.enums.RuleTargetLangEnum
import com.mola.neptune.core.parser.NeptuneRuleParser
import com.mola.neptune.core.parser.node.RuleConfig

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
        // 加载dsl
        val ruleConfig: RuleConfig = ruleParser.parseConfig(ruleDsl)
        println("ruleName: $ruleName  dsl parsed")

        // 编译成targetLang
        val script = ruleParser.parseToTargetLang(ruleConfig, RuleTargetLangEnum.GROOVY)
        println("ruleName: $ruleName  target lang parsed : \n $script")

        // 编译targetLang
        NeptuneScriptEngine.initEngine(RuleTargetLangEnum.GROOVY)
        NeptuneScriptEngine.instance.preCompile(script)
        println("ruleName: $ruleName  target lang preCompiled")

        ruleScriptMap[ruleName] = script
    }

    fun getScript(ruleName: String): String? {
        return ruleScriptMap[ruleName]
    }
}