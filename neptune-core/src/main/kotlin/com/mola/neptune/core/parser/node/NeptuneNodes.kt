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
    val matchCondition: String?,
    val value: List<SubRuleValue>
) : NeptuneRuleNode()


data class SubRuleParam(
    val type: String,
    val code: String
) : NeptuneRuleNode()


data class SubRuleValue(
    val type: String,
    val value: String,
    val dataSource: RuleDataSource
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


data class RuleDataSource(
    val type: String,
    val db: String,
    val tb: String,
    val col: String,
    val where: List<String>
) : NeptuneRuleNode()

