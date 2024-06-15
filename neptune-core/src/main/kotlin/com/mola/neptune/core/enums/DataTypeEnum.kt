package com.mola.neptune.core.enums


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 16:05
 **/
enum class DataTypeEnum(val code: String, val desc: String) {

    STRING("String", "字符串"),
    NUMBER("Number","数字类型"),
    DATE("Date", "日期"),
    LIST("List", "列表"),
    DYNAMIC("Dynamic", "动态数据源");

    fun match(code: String) : Boolean{
        return this.code == code
    }

    companion object {
        fun getByCode(code: String) : DataTypeEnum? {
            return DataTypeEnum.values().find {
                    e -> e.code == code
            }
        }
    }
}