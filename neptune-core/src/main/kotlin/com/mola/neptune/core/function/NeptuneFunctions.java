package com.mola.neptune.core.function;

import com.mola.neptune.client.RuleContext;

import java.util.Date;

/**
 * @author : molamola
 * @Project: neptune
 * @Description:
 * @date : 2024-06-02 12:53
 **/
public class NeptuneFunctions {

    public static boolean leftContainsRight(String left, String right,
                                            RuleContext ruleContext, String ruleCode) {
        if (left == null) {
            return false;
        }
        boolean result = left.contains(right);
        ruleContext.addSubRuleLog(String.format(
                "[leftContainsRight] %s execute, left = %s, right = %s, result = %s", ruleCode, left, right, result));
        return result;
    }

    public static boolean leftAfterRight(Date left, Date right,
                                         RuleContext ruleContext, String ruleCode) {
        if (left == null) {
            return false;
        }
        boolean result = left.after(right);
        ruleContext.addSubRuleLog(String.format(
                "[leftAfterRight] %s execute, left = %s, right = %s, result = %s",ruleCode, left, right, result));
        return result;
    }
}
