package com.mola.neptune.core.parser.node


data class RuleConfig(
    val ruleName: String,
    val subRules: List<SubRule>,
    val conditions: List<RuleCondition>,
    val defaultAction: RuleAction
) : NeptuneRuleNode()


data class SubRule(
    val subRuleName: String,
    val subRuleCode: String,
    val param: SubRuleParam,
    val matchMethod: String,
    val value: SubRuleValue
) : NeptuneRuleNode()


data class SubRuleParam(
    val type: String,
    val code: String,
    val structure: String
) : NeptuneRuleNode()


data class SubRuleValue(
    val type: String,
    val value: String,
    val structure: String
) : NeptuneRuleNode()


data class RuleCondition(
    val conditionName: String,
    val expression: String,
    val action: RuleAction
) : NeptuneRuleNode()

data class RuleAction(
    val type: String,
    val content: String?,
    val method: String?
) : NeptuneRuleNode()

