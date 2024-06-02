package com.mola.neptune.core.function;

import com.mola.neptune.client.RuleContext;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : molamola
 * @Project: neptune
 * @Description:
 * @date : 2024-06-02 12:53
 **/
public class NeptuneFunctions {

    public static boolean stringContains(String left, String right,
                                            RuleContext ruleContext, String ruleCode) {
        if (left == null) {
            return false;
        }
        boolean result = left.contains(right);
        ruleContext.addSubRuleLog(String.format(
                "[leftContainsRight] %s execute, left = %s, right = %s, result = %s", ruleCode, left, right, result));
        return result;
    }

    public static boolean dateAfter(Date left, Date right,
                                         RuleContext ruleContext, String ruleCode) {
        if (left == null) {
            return false;
        }
        boolean result = left.after(right);
        ruleContext.addSubRuleLog(String.format(
                "[leftAfterRight] %s execute, left = %s, right = %s, result = %s",ruleCode, left, right, result));
        return result;
    }

    public static boolean leftGreaterThanRight(String left, String right,
                                               RuleContext ruleContext, String ruleCode) {
        if (left == null || right == null) {
            return false;
        }
        BigDecimal leftBd = new BigDecimal(left);
        BigDecimal rightBd = new BigDecimal(right);
        boolean result = leftBd.compareTo(rightBd) > 0;
        ruleContext.addSubRuleLog(String.format(
                "[leftGreaterThanRight] %s execute, left = %s, right = %s, result = %s",ruleCode, left, right, result));
        return result;
    }

    public static boolean leftGreaterOrEqualRight(String left, String right,
                                               RuleContext ruleContext, String ruleCode) {
        if (left == null || right == null) {
            return false;
        }
        BigDecimal leftBd = new BigDecimal(left);
        BigDecimal rightBd = new BigDecimal(right);
        boolean result = leftBd.compareTo(rightBd) >= 0;
        ruleContext.addSubRuleLog(String.format(
                "[leftGreaterOrEqualRight] %s execute, left = %s, right = %s, result = %s",ruleCode, left, right, result));
        return result;
    }

    public static boolean leftEqualRight(String left, String right,
                                               RuleContext ruleContext, String ruleCode) {
        if (left == null || right == null) {
            return false;
        }
        BigDecimal leftBd = new BigDecimal(left);
        BigDecimal rightBd = new BigDecimal(right);
        boolean result = leftBd.compareTo(rightBd) == 0;
        ruleContext.addSubRuleLog(String.format(
                "[leftEqualRight] %s execute, left = %s, right = %s, result = %s",ruleCode, left, right, result));
        return result;
    }

    public static boolean leftLessThanRight(String left, String right,
                                               RuleContext ruleContext, String ruleCode) {
        if (left == null || right == null) {
            return false;
        }
        BigDecimal leftBd = new BigDecimal(left);
        BigDecimal rightBd = new BigDecimal(right);
        boolean result = leftBd.compareTo(rightBd) < 0;
        ruleContext.addSubRuleLog(String.format(
                "[leftLessThanRight] %s execute, left = %s, right = %s, result = %s",ruleCode, left, right, result));
        return result;
    }

    public static boolean leftLessOrEqualRight(String left, String right,
                                                  RuleContext ruleContext, String ruleCode) {
        if (left == null || right == null) {
            return false;
        }
        BigDecimal leftBd = new BigDecimal(left);
        BigDecimal rightBd = new BigDecimal(right);
        boolean result = leftBd.compareTo(rightBd) <= 0;
        ruleContext.addSubRuleLog(String.format(
                "[leftLessOrEqualRight] %s execute, left = %s, right = %s, result = %s",ruleCode, left, right, result));
        return result;
    }
}
