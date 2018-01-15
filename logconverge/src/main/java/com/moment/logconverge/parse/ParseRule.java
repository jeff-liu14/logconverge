package com.moment.logconverge.parse;

import com.moment.logconverge.type.ParseType;

/**
 * 解析规则
 * Created by moment on 2018/1/10.
 */

public class ParseRule {

    //默认json格式化日志
    private static ParseType defaultRule = ParseType.JSON;

    public static ParseType getDefaultRule() {
        return defaultRule;
    }

    public static void setDefaultRule(ParseType parseType) {
        defaultRule = parseType;
    }
}
