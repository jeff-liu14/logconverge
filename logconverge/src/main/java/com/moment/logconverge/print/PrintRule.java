package com.moment.logconverge.print;

import com.moment.logconverge.type.PrintType;

/**
 * 日志打印规则
 * Created by moment on 2018/1/10.
 */

public class PrintRule {

    //默认控制台打印日志
    private static PrintType defaultRule = PrintType.LOGGCAT;

    public static PrintType getDefaultRule() {
        return defaultRule;
    }

    public static void setDefaultRule(PrintType logShowType) {
        defaultRule = logShowType;
    }
}
