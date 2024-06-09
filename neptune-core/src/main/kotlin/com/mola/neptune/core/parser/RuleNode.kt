package com.mola.neptune.core.parser

import com.mola.neptune.core.entity.*
import com.mola.neptune.core.enums.RuleTargetLangEnum
import com.mola.neptune.core.generator.RuleGenerator
import com.mola.neptune.core.generator.groovy.*


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:33
 **/
abstract class RuleNode {

    companion object {
        private val groovyRuleGeneratorMap :
                MutableMap<Class<out RuleNode>, RuleGenerator<RuleNode>> = mutableMapOf()
        init {
            groovyRuleGeneratorMap[RuleConfig::class.java] = GroovyRuleConfigGenerator()
            groovyRuleGeneratorMap[RuleAction::class.java] = GroovyRuleActionGenerator()
            groovyRuleGeneratorMap[RuleCondition::class.java] = GroovyRuleConditionGenerator()
            groovyRuleGeneratorMap[RuleDataSource::class.java] = GroovyRuleDataSourceGenerator()
            groovyRuleGeneratorMap[SubRule::class.java] = GroovySubRuleGenerator()
            groovyRuleGeneratorMap[SubRuleParam::class.java] = GroovySubRuleParamGenerator()
            groovyRuleGeneratorMap[SubRuleValue::class.java] = GroovySubRuleValueGenerator()
        }
    }

    /**
     * 接受访问
     */
    fun accept(visitor: NeptuneRulePartVisitor) {
        var ruleGenerator: RuleGenerator<RuleNode>? = null
        if (visitor.getTargetLangType() == RuleTargetLangEnum.GROOVY) {
            ruleGenerator = groovyRuleGeneratorMap[this::class.java]
        }
        ruleGenerator?.generate(this, visitor)
    }
}