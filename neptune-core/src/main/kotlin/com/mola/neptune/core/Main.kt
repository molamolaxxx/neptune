package com.mola.neptune.core

import com.mola.neptune.client.NeptuneClient
import com.mola.neptune.client.NeptuneRequest
import com.mola.neptune.core.core.NeptuneClientImpl
import com.mola.neptune.core.core.NeptuneExecutor
import com.mola.neptune.core.core.NeptuneRuleLoader
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

fun main() {

    // 加载规则
    val ruleJson: String = try {
        String(Files.readAllBytes(Paths.get("rule.json")))
    } catch (e: IOException) {
        throw RuntimeException(e)
    }
    NeptuneRuleLoader.loadRule("测试规则1", ruleJson)

    val cl: NeptuneClient = NeptuneClientImpl
    val result = cl.execute(buildReq())
    println(result.content)

    NeptuneExecutor.shutdown()
}

fun buildReq(): NeptuneRequest {
    val request = NeptuneRequest()
    request.ruleName = "测试规则1"
    val inputMap: MutableMap<String, Any> = mutableMapOf()
    inputMap["param1"] = 3
    inputMap["param2"] = "check it out"
    inputMap["date1"] = Date()
    inputMap["param4"] = ""
    val dataSource: MutableMap<String, Any> = mutableMapOf()
    dataSource["input"] = inputMap
    request.paramMap = dataSource
    return request
}