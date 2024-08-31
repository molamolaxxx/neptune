package com.mola.neptune.core.parser.generator.groovy

import com.mola.neptune.core.enums.DataStructureEnum
import com.mola.neptune.core.enums.DataTypeEnum
import com.mola.neptune.core.parser.NeptuneRulePartVisitor
import com.mola.neptune.core.parser.generator.RuleGenerator
import com.mola.neptune.core.parser.node.SubRuleValue
import com.mola.neptune.core.util.JsonUtil
import java.util.Date


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:44
 **/
class GroovySubRuleValueGenerator : RuleGenerator<SubRuleValue> {

    override fun generate(node: SubRuleValue, visitor: NeptuneRulePartVisitor) {
        val type = node.type
        val value = node.value
        val structure = node.structure

        // 获取数据类型&结构
        val valueType = DataTypeEnum.getByCode(type)
        val valueStructure = DataStructureEnum.getByCode(structure)

        // 类型检查
        checkValueByType(value, valueType, valueStructure)

        visitor.addTemp("NeptuneMatchFunctions.fetchAndParseValue('${node.value}', '${node.type}','${node.structure}')")
    }

    private fun checkValueByType(value: String, type: DataTypeEnum, structure: DataStructureEnum) {
        try {
            // 检查是否是列表
            if (structure == DataStructureEnum.LIST) {
                for (any in JsonUtil.parseJson(value, List::class.java)) {
                    checkValueByType(any.toString(), type)
                }
            } else if (structure == DataStructureEnum.SINGLE) {
                checkValueByType(value, type)
            }
        } catch (e: Exception) {
            throw RuntimeException("check type failed , type = ${type.code}, structure = ${structure.code}")
        }
    }

    private fun checkValueByType(value: String, type: DataTypeEnum) {
        if (DataTypeEnum.NUMBER == type) {
            value.toLong()
        } else if (DataTypeEnum.DATE == type) {
            Date(value.toLong())
        }
    }
}