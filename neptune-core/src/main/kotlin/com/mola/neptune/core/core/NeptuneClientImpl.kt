package com.mola.neptune.core.core

import com.mola.neptune.client.NeptuneClient
import com.mola.neptune.client.NeptuneRequest
import com.mola.neptune.client.NeptuneResult

/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:18
 **/
object NeptuneClientImpl : NeptuneClient {

    override fun execute(neptuneRequest: NeptuneRequest): NeptuneResult {
        return NeptuneExecutor.execute(neptuneRequest)
    }
}