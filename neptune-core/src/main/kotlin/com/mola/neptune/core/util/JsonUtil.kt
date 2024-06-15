package com.mola.neptune.core.util

import com.google.gson.Gson

/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:33
 **/
object JsonUtil {

    fun <T> parseJson(json: String, clazz: Class<T>): T {
        return Gson().fromJson(json, clazz)
    }

    fun toJson(obj: Any): String {
        return Gson().toJson(obj)
    }

    fun formatJsonArr(listStr: String): String {
        return toJson(parseJson(listStr, List::class.java))
    }
}