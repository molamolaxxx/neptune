package com.mola.neptune.core.generator.groovy

import com.alibaba.fastjson.JSON
import com.mola.neptune.core.entity.RuleDataSource
import com.mola.neptune.core.enums.DataSourceTypeEnum
import com.mola.neptune.core.generator.RuleGenerator
import com.mola.neptune.core.parser.NeptuneRulePartVisitor


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-09 23:43
 **/
class GroovyRuleDataSourceGenerator : RuleGenerator<RuleDataSource> {

    override fun generate(node: RuleDataSource, visitor: NeptuneRulePartVisitor) {
        if (node.type == DataSourceTypeEnum.MYSQL.code) {
            visitor.addTemp("NeptuneDataSourceFunctions" +
                    ".fetchFromMysql('${node.db}', '${node.tb}','${node.col}', " +
                    "${JSON.toJSONString(node.where)})")
            return
        }
        if (node.type == DataSourceTypeEnum.REDIS.code) {
            visitor.addTemp("NeptuneDataSourceFunctions" +
                    ".fetchFromRedis('${node.db}', ${JSON.toJSONString(node.where)})")
            return
        }
        throw RuntimeException("un support dataSource, type = ${node.type}")
    }
}