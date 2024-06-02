package com.mola.neptune.core.enums


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 16:05
 **/
enum class DataSourceTypeEnum(val code: String, val desc: String) {

    MYSQL("mysql", "Mysql数据库"),

    REDIS("redis", "Redis数据库")
}