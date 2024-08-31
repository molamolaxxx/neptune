package com.mola.neptune.core.enums


/**
 * @Project: neptune
 * @Description: 匹配规则
 * @author : molamola
 * @date : 2024-06-02 16:05
 **/
enum class MatchMethodEnum(val code: String, val desc: String, val display: String) {

    STRING_CONTAINS("stringContains", "字符串包含", "包含"),

    DATE_AFTER("dateAfter","日期比较", "晚于"),

    LEFT_GREATER_THAN_RIGHT("leftGreaterThanRight", "大于", ">"),

    LEFT_GREATER_OR_EQUAL_RIGHT("leftGreaterOrEqualRight", "大于等于", ">="),

    LEFT_EQUAL_RIGHT("leftEqualRight", "等于", "=="),

    LEFT_LESS_THAN_RIGHT("leftGreaterThanRight", "小于", "<"),

    LEFT_LESS_OR_EQUAL_RIGHT("leftGreaterOrEqualRight", "小于等于", "<="),

    CONTAINS("contains", "包含子元素", "包含")
    ;

    companion object {
        fun getByCode(code: String): MatchMethodEnum? {
            for (value in MatchMethodEnum.values()) {
                if (code == value.code) {
                    return value
                }
            }
            return null
        }
    }
}