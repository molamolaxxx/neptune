package com.mola.neptune.core.parser.generator

import com.mola.neptune.core.parser.NeptuneRulePartVisitor
import com.mola.neptune.core.parser.node.RuleNode


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-09 23:10
 **/
interface RuleGenerator<out T : RuleNode> {

    /**
     * 接受visitor的访问，生成target代码
     * @param node 规则节点
     * @param visitor 规则访问器
     */
    fun generate(node: @UnsafeVariance T, visitor: NeptuneRulePartVisitor)
}