package com.mola.neptune.core.core

import com.mola.neptune.client.NeptuneRequest
import com.mola.neptune.client.NeptuneResult
import java.util.concurrent.*
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy
import java.util.concurrent.atomic.AtomicInteger
import javax.script.Bindings
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.SimpleBindings

/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:18
 **/
object NeptuneExecutor {

    private val ruleExecuteExecutor: ThreadPoolExecutor = ThreadPoolExecutor(20, 100
        , 200, TimeUnit.MILLISECONDS, ArrayBlockingQueue(1024), object : ThreadFactory {
        private val threadIndex = AtomicInteger(0)
        override fun newThread(r: Runnable): Thread {
            return NeptuneExecThread(r, threadIndex.incrementAndGet())
        }
    }, CallerRunsPolicy())

    /**
     * 规则引擎执行
     */
    fun execute(neptuneRequest: NeptuneRequest): NeptuneResult {
        val future:Future<NeptuneResult> = ruleExecuteExecutor.submit<NeptuneResult> {
            if (neptuneRequest.ruleName == null) {
                throw RuntimeException("ruleName not null")
            }

            val script = NeptuneRuleLoader.getScript(neptuneRequest.ruleName!!)

            try {
                return@submit NeptuneScriptEngine.instance.eval(script!!, neptuneRequest.paramMap)
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
        return future.get() as NeptuneResult
    }

    fun shutdown() {
        ruleExecuteExecutor.shutdown()
    }

    class NeptuneExecThread(target: Runnable?, idx: Int?) :
        Thread(target, "neptune-execute-thread-${idx}")
}