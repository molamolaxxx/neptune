package com.mola.neptune.client


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:18
 **/
class NeptuneRequest {

    /**
     * 规则名称指定
     */
    var ruleName: String? = null

    /**
     * 参数
     */
    var paramMap: Map<String, Any> = mutableMapOf()
}