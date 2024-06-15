package com.mola.neptune.core.parser

import com.mola.neptune.core.enums.RuleTargetLangEnum
import com.mola.neptune.core.parser.node.RuleConfig
import com.mola.neptune.core.util.JsonUtil


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:31
 **/
class NeptuneRuleParser {

    fun parseConfig(json: String): RuleConfig {
        return JsonUtil.parseJson(json, RuleConfig::class.java)
    }

    fun parseToTargetLang(ruleConfig: RuleConfig, langType: RuleTargetLangEnum): String {
        val ruleVisitor = NeptuneRulePartVisitor()
        ruleVisitor.ruleTargetLangEnum = langType
        ruleConfig.accept(ruleVisitor)
        return ruleVisitor.getTargetLang()
    }
}