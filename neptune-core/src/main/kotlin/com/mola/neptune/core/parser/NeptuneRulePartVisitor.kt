package com.mola.neptune.core.parser

import com.mola.neptune.core.enums.RuleTargetLangEnum


/**
 * @Project: neptune
 * @Description:
 * @author : molamola
 * @date : 2024-06-02 11:34
 **/
class NeptuneRulePartVisitor {

    private val targetLangBuf = StringBuilder()

    private var tempBuffer = StringBuilder()

    var ruleTargetLangEnum: RuleTargetLangEnum = RuleTargetLangEnum.GROOVY

    fun getTargetLang(): String {
        return targetLangBuf.toString()
    }

    fun addLine(line: String) {
        targetLangBuf.append(line)
        targetLangBuf.append("\n")
    }

    fun newLine() {
        targetLangBuf.append("\n")
    }

    fun newTab() {
        targetLangBuf.append("\t")
    }

    fun add(part: String) {
        targetLangBuf.append(part)
    }


    fun addTemp(part: String) {
        tempBuffer.append(part)
    }

    fun getTemp(): String {
        val temp = tempBuffer.toString()
        tempBuffer = StringBuilder()
        return temp
    }
}