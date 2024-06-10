package com.mola.neptune.core.parser

import com.alibaba.fastjson.JSON
import com.mola.neptune.core.entity.RuleConfig
import com.mola.neptune.core.enums.RuleTargetLangEnum


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:31
 **/
class NeptuneRuleParser {

    fun parseConfig(json: String?): RuleConfig {
        return JSON.parseObject(json, RuleConfig::class.java)
    }

    fun parseToTargetLang(ruleConfig: RuleConfig, langType: RuleTargetLangEnum): String {
        val ruleVisitor = NeptuneRulePartVisitor()
        ruleVisitor.ruleTargetLangEnum = langType
        ruleConfig.accept(ruleVisitor)
        return ruleVisitor.getTargetLang()
    }
}