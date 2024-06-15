package com.mola.neptune.core.parser.generator.groovy

import com.mola.neptune.core.enums.ActionTypeEnum
import com.mola.neptune.core.parser.generator.base.RuleConfigGenerator
import com.mola.neptune.core.parser.NeptuneRulePartVisitor
import com.mola.neptune.core.parser.node.RuleConfig


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-03 20:54
 **/
class GroovyRuleConfigGenerator : RuleConfigGenerator() {

    override fun generateDescription(node: RuleConfig, visitor: NeptuneRulePartVisitor) {
        visitor.addLine("// rule engine :${node.ruleName}")
    }

    override fun generateImportDependency(node: RuleConfig, visitor: NeptuneRulePartVisitor) {
        visitor.addLine("import com.mola.neptune.client.NeptuneResult")
        visitor.addLine("import com.mola.neptune.core.function.NeptuneMatchFunctions")
        visitor.addLine("import com.mola.neptune.core.function.NeptuneDataSourceFunctions")
        visitor.addLine("import com.mola.neptune.client.RuleContext")
        visitor.newLine()
    }

    override fun generateContext(node: RuleConfig, visitor: NeptuneRulePartVisitor) {
        visitor.addLine("// 初始化上下文")
        visitor.addLine("def ctx = new RuleContext()")
        visitor.newLine()
    }

    override fun generateDefaultAction(node: RuleConfig, visitor: NeptuneRulePartVisitor) {
        val defaultAction = node.defaultAction
        defaultAction.accept(visitor)
        if (ActionTypeEnum.RETURN.match(defaultAction.type)) {
            return
        }
        visitor.addLine("return new NeptuneResult(ctx)")
    }
}