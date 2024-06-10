package com.mola.neptune.core.core

import com.mola.neptune.client.NeptuneResult
import com.mola.neptune.core.enums.RuleTargetLangEnum
import org.codehaus.groovy.jsr223.GroovyScriptEngineImpl
import javax.script.Bindings
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.SimpleBindings


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-10 12:23
 **/
class NeptuneScriptEngine(targetLangEnum: RuleTargetLangEnum) {

    private lateinit var scriptEngine : ScriptEngine

    private var targetLangEnum : RuleTargetLangEnum

    init {
        this.targetLangEnum = targetLangEnum
        if (targetLangEnum == RuleTargetLangEnum.GROOVY) {
            val manager = ScriptEngineManager()
            scriptEngine = manager.getEngineByName("groovy")
        }
    }

    fun eval(script: String, paramMap: Map<String, Any>) : NeptuneResult {
        if (targetLangEnum == RuleTargetLangEnum.GROOVY) {
            // 创建 Bindings 对象并设置 input 参数
            val bindings: Bindings = SimpleBindings()
            for (key in paramMap.keys) {
                bindings[key] = paramMap[key]
            }
            return scriptEngine.eval(script, bindings) as NeptuneResult
        }
        throw RuntimeException("unknown targetLangEnum $targetLangEnum")
    }

    fun preCompile(script: String) {
        if (targetLangEnum == RuleTargetLangEnum.GROOVY) {
            val groovyEngine = scriptEngine as GroovyScriptEngineImpl
            groovyEngine.compile(script)
        }
    }

    companion object {

        lateinit var instance: NeptuneScriptEngine

        fun initEngine(targetLangEnum: RuleTargetLangEnum) {
            instance = NeptuneScriptEngine(targetLangEnum)
        }
    }
}