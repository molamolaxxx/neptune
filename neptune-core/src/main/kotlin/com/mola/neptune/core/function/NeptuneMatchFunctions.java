package com.mola.neptune.core.function;

import com.mola.neptune.client.RuleContext;
import com.mola.neptune.core.enums.DataStructureEnum;
import com.mola.neptune.core.enums.DataTypeEnum;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author : molamola
 * @Project: neptune
 * @Description:
 * @date : 2024-06-02 12:53
 **/
public class NeptuneMatchFunctions {

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

    public static boolean stringContains(String left, List<String> rightValues,
                                         RuleContext ruleContext, String ruleCode) {
        return rightValues.stream().anyMatch(right ->
                NeptuneMatchFunctions.stringContains(left, right, ruleContext, ruleCode));
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

    public static boolean leftGreaterThanRight(BigDecimal left, BigDecimal right,
                                               RuleContext ruleContext, String ruleCode) {
        if (left == null || right == null) {
            return false;
        }
        boolean result = left.compareTo(right) > 0;
        ruleContext.addSubRuleLog(String.format(
                "[leftGreaterThanRight] %s execute, left = %s, right = %s, result = %s",ruleCode, left, right, result));
        return result;
    }

    public static boolean leftGreaterOrEqualRight(BigDecimal left, BigDecimal right,
                                               RuleContext ruleContext, String ruleCode) {
        if (left == null || right == null) {
            return false;
        }
        boolean result = left.compareTo(right) >= 0;
        ruleContext.addSubRuleLog(String.format(
                "[leftGreaterOrEqualRight] %s execute, left = %s, right = %s, result = %s",ruleCode, left, right, result));
        return result;
    }

    public static boolean leftEqualRight(BigDecimal left, BigDecimal right,
                                               RuleContext ruleContext, String ruleCode) {
        if (left == null || right == null) {
            return false;
        }
        boolean result = left.compareTo(right) == 0;
        ruleContext.addSubRuleLog(String.format(
                "[leftEqualRight] %s execute, left = %s, right = %s, result = %s",ruleCode, left, right, result));
        return result;
    }

    public static boolean leftEqualRight(String left, String right,
                                         RuleContext ruleContext, String ruleCode) {
        if (left == null || right == null) {
            return false;
        }
        boolean result = Objects.equals(left, right);
        ruleContext.addSubRuleLog(String.format(
                "[leftEqualRight] %s execute, left = %s, right = %s, result = %s",ruleCode, left, right, result));
        return result;
    }

    public static boolean leftEqualRight(String left, List<String> rightValues,
                                         RuleContext ruleContext, String ruleCode) {

        return rightValues.stream().anyMatch(right ->
                NeptuneMatchFunctions.leftEqualRight(left, right, ruleContext, ruleCode));
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

    public static boolean contains(List<String> left, String right,
                                 RuleContext ruleContext, String ruleCode) {
        if (left == null || right == null) {
            return false;
        }
        boolean result = left.contains(right);
        ruleContext.addSubRuleLog(String.format(
                "[contains] %s execute, left = %s, right = %s, result = %s",
                ruleCode, left, right, result));
        return result;
    }

    public static Object fetchAndParseValue(String valueStr, String type, String structure) {
        if (DataTypeEnum.STRING.match(type)) {
            return valueStr;
        }
        if (DataTypeEnum.NUMBER.match(type)) {
            return new BigDecimal(valueStr);
        }
        if (DataTypeEnum.DATE.match(type)) {
            return new Date(Long.parseLong(valueStr));
        }
        return null;
    }

    public static Object fetchAndParseParam(Object param, String type, String structure) {
        if (DataStructureEnum.LIST.match(structure)) {
            List<Object> parsed = new ArrayList<>();
            for (Object each : (List) param) {
                parsed.add(fetchAndParseParamSingle(each, type, structure));
            }
            return parsed;
        } else {
            return fetchAndParseParamSingle(param, type, structure);
        }
    }


    public static Object fetchAndParseParamSingle(Object param, String type, String structure) {
        if (DataTypeEnum.STRING.match(type)) {
            if (param instanceof String) {
                return param;
            }
            return param.toString();
        }
        if (DataTypeEnum.NUMBER.match(type)) {
            if (param instanceof BigDecimal) {
                return param;
            }
            return new BigDecimal(param.toString());
        }
        if (DataTypeEnum.DATE.match(type)) {
            if (param instanceof Date) {
                return param;
            }
            if (param instanceof String) {
                return new Date(Long.parseLong(param.toString()));
            }
        }
        return null;
    }
}
