package com.mola.neptune.core.core

import com.mola.neptune.core.enums.DataStructureEnum
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
        // string
        MatchMethodMapping(DataTypeEnum.STRING, MatchMethodEnum.STRING_CONTAINS,
            DataTypeEnum.STRING,
            listOf(DataStructureEnum.SINGLE),
            listOf(DataStructureEnum.LIST, DataStructureEnum.SINGLE)),

        MatchMethodMapping(DataTypeEnum.STRING, MatchMethodEnum.LEFT_EQUAL_RIGHT,
            DataTypeEnum.STRING,
            listOf(DataStructureEnum.SINGLE),
            listOf(DataStructureEnum.LIST, DataStructureEnum.SINGLE)),

        MatchMethodMapping(DataTypeEnum.STRING, MatchMethodEnum.CONTAINS,
            DataTypeEnum.STRING,
            listOf(DataStructureEnum.LIST),
            listOf(DataStructureEnum.LIST, DataStructureEnum.SINGLE)),

        // date
        MatchMethodMapping(DataTypeEnum.DATE, MatchMethodEnum.DATE_AFTER,
            DataTypeEnum.DATE,
            listOf(DataStructureEnum.SINGLE),
            listOf(DataStructureEnum.SINGLE)),

        // number
        MatchMethodMapping(DataTypeEnum.NUMBER, MatchMethodEnum.LEFT_GREATER_THAN_RIGHT,
            DataTypeEnum.NUMBER,
            listOf(DataStructureEnum.SINGLE),
            listOf(DataStructureEnum.SINGLE)),

        MatchMethodMapping(DataTypeEnum.NUMBER, MatchMethodEnum.LEFT_GREATER_OR_EQUAL_RIGHT,
            DataTypeEnum.NUMBER,
            listOf(DataStructureEnum.SINGLE),
            listOf(DataStructureEnum.SINGLE)),

        MatchMethodMapping(DataTypeEnum.NUMBER, MatchMethodEnum.LEFT_EQUAL_RIGHT,
            DataTypeEnum.NUMBER,
            listOf(DataStructureEnum.SINGLE),
            listOf(DataStructureEnum.SINGLE, DataStructureEnum.LIST)),

        MatchMethodMapping(DataTypeEnum.NUMBER, MatchMethodEnum.LEFT_LESS_THAN_RIGHT,
            DataTypeEnum.NUMBER,
            listOf(DataStructureEnum.SINGLE),
            listOf(DataStructureEnum.SINGLE)),

        MatchMethodMapping(DataTypeEnum.NUMBER, MatchMethodEnum.LEFT_LESS_OR_EQUAL_RIGHT,
            DataTypeEnum.NUMBER,
            listOf(DataStructureEnum.SINGLE),
            listOf(DataStructureEnum.SINGLE)),
    )

    fun match(paramType: String, matchMethod: String, valueType: String,
              paramStructure: String, valueStructure: String): Boolean {
        val paramStructureEnum = DataStructureEnum.getByCode(paramStructure)
        val valueStructureEnum = DataStructureEnum.getByCode(valueStructure)
        val matchedMapping = matchMethodList.find { mapping ->
            mapping.paramType == DataTypeEnum.getByCode(paramType) &&
                    mapping.valueType == DataTypeEnum.getByCode(valueType) &&
                    mapping.matchMethod == MatchMethodEnum.getByCode(matchMethod) &&
                    (paramStructureEnum == DataStructureEnum.DYNAMIC || mapping.paramDataStructure.contains(paramStructureEnum)) &&
                    (valueStructureEnum == DataStructureEnum.DYNAMIC || mapping.valueDataStructure.contains(valueStructureEnum))
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
    val valueType: DataTypeEnum,
    val paramDataStructure: List<DataStructureEnum>,
    val valueDataStructure: List<DataStructureEnum>
)