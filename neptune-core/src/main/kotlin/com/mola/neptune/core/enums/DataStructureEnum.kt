package com.mola.neptune.core.enums


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 16:05
 **/
enum class DataStructureEnum(val code: String, val desc: String) {

    LIST("list", "列表"),
    SINGLE("single","单个元素"),
    DYNAMIC("dynamic", "动态数据源");

    fun match(code: String) : Boolean{
        return this.code == code
    }

    companion object {
        fun getByCode(code: String) : DataStructureEnum {
            return DataStructureEnum.values().find {
                    e -> e.code == code
            }!!
        }
    }
}