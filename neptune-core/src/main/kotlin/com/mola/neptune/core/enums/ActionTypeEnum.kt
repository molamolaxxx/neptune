package com.mola.neptune.core.enums


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 16:05
 **/
enum class ActionTypeEnum(val code: String, val desc: String) {

    RETURN("return", "返回值"),;

    fun match(code: String) : Boolean{
        return this.code == code
    }
}