package com.mola.neptune.client


/**
 * @Project: neptune
 * @Description: 海王星规则执行客户端
 * @author : molamola
 * @date : 2024-06-02 11:17
 **/
interface NeptuneClient {

    /**
     * 执行规则
     */
    fun execute(neptuneRequest: NeptuneRequest): NeptuneResult
}