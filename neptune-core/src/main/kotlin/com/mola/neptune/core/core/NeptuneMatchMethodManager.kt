package com.mola.neptune.core.core

import com.mola.neptune.core.enums.DataTypeEnum
import com.mola.neptune.core.enums.MatchMethodEnum

/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:18
 **/
object NeptuneMatchMethodManager {

    private val matchMethodList: List<MatchMethodMapping> = listOf(
        MatchMethodMapping(DataTypeEnum.STRING, MatchMethodEnum.STRING_CONTAINS, DataTypeEnum.STRING),
        MatchMethodMapping(DataTypeEnum.DATE, MatchMethodEnum.DATE_AFTER, DataTypeEnum.DATE),
        MatchMethodMapping(DataTypeEnum.NUMBER, MatchMethodEnum.LEFT_GREATER_THAN_RIGHT, DataTypeEnum.NUMBER),
        MatchMethodMapping(DataTypeEnum.NUMBER, MatchMethodEnum.LEFT_GREATER_OR_EQUAL_RIGHT, DataTypeEnum.NUMBER),
        MatchMethodMapping(DataTypeEnum.NUMBER, MatchMethodEnum.LEFT_EQUAL_RIGHT, DataTypeEnum.NUMBER),
        MatchMethodMapping(DataTypeEnum.STRING, MatchMethodEnum.LEFT_EQUAL_RIGHT, DataTypeEnum.STRING),
        MatchMethodMapping(DataTypeEnum.NUMBER, MatchMethodEnum.LEFT_LESS_THAN_RIGHT, DataTypeEnum.NUMBER),
        MatchMethodMapping(DataTypeEnum.NUMBER, MatchMethodEnum.LEFT_LESS_OR_EQUAL_RIGHT, DataTypeEnum.NUMBER),
        MatchMethodMapping(DataTypeEnum.STRING, MatchMethodEnum.IN_LIST, DataTypeEnum.DYNAMIC),
        MatchMethodMapping(DataTypeEnum.LIST, MatchMethodEnum.CONTAINS, DataTypeEnum.STRING)
    )

    fun match(paramType: String, matchMethod: String, valueType: String): Boolean {
        val matchedMapping = matchMethodList.find { mapping ->
            mapping.paramType == DataTypeEnum.getByCode(paramType) &&
                    mapping.valueType == DataTypeEnum.getByCode(valueType) &&
                    mapping.matchMethod == MatchMethodEnum.getByCode(matchMethod)
        }
        return matchedMapping != null
    }

    fun find(paramType: String?, matchMethod: String?, valueType: String?): List<MatchMethodMapping> {
        return matchMethodList.filter { mapping ->
            (paramType == null || mapping.paramType == DataTypeEnum.getByCode(paramType))&&
            (valueType == null || mapping.valueType == DataTypeEnum.getByCode(valueType)) &&
            (matchMethod == null || mapping.matchMethod == MatchMethodEnum.getByCode(matchMethod))
        }
    }
}

data class MatchMethodMapping(
    val paramType: DataTypeEnum,
    val matchMethod: MatchMethodEnum,
    val valueType: DataTypeEnum
)