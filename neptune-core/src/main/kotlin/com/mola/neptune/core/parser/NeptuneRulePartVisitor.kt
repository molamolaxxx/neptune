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

    private var ruleTargetLangEnum: RuleTargetLangEnum = RuleTargetLangEnum.GROOVY

    fun getTargetLang(): String {
        return targetLangBuf.toString()
    }

    fun getTargetLangType(): RuleTargetLangEnum {
        return ruleTargetLangEnum
    }

    fun addLine(line: String) {
        targetLangBuf.append(line)
        targetLangBuf.append("\n")
    }

    fun newLine() {
        targetLangBuf.append("\n")
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