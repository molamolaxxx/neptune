package com.mola.neptune.core.parser.node

import com.mola.neptune.core.enums.RuleTargetLangEnum
import com.mola.neptune.core.parser.generator.RuleGenerator
import com.mola.neptune.core.parser.NeptuneRulePartVisitor
import com.mola.neptune.core.parser.generator.groovy.*
import kotlin.reflect.KClass


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:33
 **/
abstract class NeptuneRuleNode {

    companion object {
        private val groovyRuleGeneratorMap :
                MutableMap<KClass<out NeptuneRuleNode>, RuleGenerator<NeptuneRuleNode>> = mutableMapOf()
        init {
            groovyRuleGeneratorMap[RuleConfig::class] = GroovyRuleConfigGenerator()
            groovyRuleGeneratorMap[RuleAction::class] = GroovyRuleActionGenerator()
            groovyRuleGeneratorMap[RuleCondition::class] = GroovyRuleConditionGenerator()
            groovyRuleGeneratorMap[SubRule::class] = GroovySubRuleGenerator()
            groovyRuleGeneratorMap[SubRuleParam::class] = GroovySubRuleParamGenerator()
            groovyRuleGeneratorMap[SubRuleValue::class] = GroovySubRuleValueGenerator()
        }
    }

    /**
     * 接受访问
     */
    fun accept(visitor: NeptuneRulePartVisitor) {
        var ruleGenerator: RuleGenerator<NeptuneRuleNode>? = null
        if (visitor.ruleTargetLangEnum == RuleTargetLangEnum.GROOVY) {
            ruleGenerator = groovyRuleGeneratorMap[this::class]
        }
        ruleGenerator?.generate(this, visitor)
    }
}