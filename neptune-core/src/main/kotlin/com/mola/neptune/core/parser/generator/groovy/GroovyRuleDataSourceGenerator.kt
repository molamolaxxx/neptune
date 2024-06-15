package com.mola.neptune.core.parser.generator.groovy

import com.alibaba.fastjson.JSON
import com.mola.neptune.core.enums.DataSourceTypeEnum
import com.mola.neptune.core.parser.generator.RuleGenerator
import com.mola.neptune.core.parser.NeptuneRulePartVisitor
import com.mola.neptune.core.parser.node.RuleDataSource


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-09 23:43
 **/
class GroovyRuleDataSourceGenerator : RuleGenerator<RuleDataSource> {

    override fun generate(node: RuleDataSource, visitor: NeptuneRulePartVisitor) {
        if (DataSourceTypeEnum.MYSQL.match(node.type)) {
            visitor.addTemp("NeptuneDataSourceFunctions" +
                    ".fetchFromMysql('${node.db}', '${node.tb}','${node.col}', " +
                    "${JSON.toJSONString(node.where)})")
            return
        }
        if (DataSourceTypeEnum.REDIS.match(node.type)) {
            visitor.addTemp("NeptuneDataSourceFunctions" +
                    ".fetchFromRedis('${node.db}', ${JSON.toJSONString(node.where)})")
            return
        }
        throw RuntimeException("un support dataSource, type = ${node.type}")
    }
}