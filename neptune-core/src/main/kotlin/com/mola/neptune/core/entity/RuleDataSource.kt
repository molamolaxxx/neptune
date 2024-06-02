package com.mola.neptune.core.entity

import com.alibaba.fastjson.JSON
import com.mola.neptune.core.enums.DataSourceTypeEnum
import com.mola.neptune.core.parser.NeptuneRulePartVisitor
import com.mola.neptune.core.parser.RuleParts


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:45
 **/
class RuleDataSource: RuleParts {

    lateinit var type: String

    lateinit var db: String

    lateinit var tb: String

    lateinit var col: String

    lateinit var where: List<String>

    override fun accept(visitor: NeptuneRulePartVisitor) {
        if (type == DataSourceTypeEnum.MYSQL.code) {
            visitor.addTemp("NeptuneDataSourceFunctions" +
                    ".fetchFromMysql('$db', '$tb','$col', ${JSON.toJSONString(where)})")
            return
        }
        if (type == DataSourceTypeEnum.REDIS.code) {
            visitor.addTemp("NeptuneDataSourceFunctions" +
                    ".fetchFromRedis('$db', ${JSON.toJSONString(where)})")
            return
        }
        throw RuntimeException("un support dataSource, type = $type")
    }
}