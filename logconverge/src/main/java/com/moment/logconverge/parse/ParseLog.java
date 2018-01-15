package com.moment.logconverge.parse;

import com.moment.logconverge.delegent.LogProxy;
import com.moment.logconverge.entity.BusinessLog;
import com.moment.logconverge.entity.CommonLog;
import com.moment.logconverge.entity.ExceptionLog;
import com.moment.logconverge.entity.ActionLog;
import com.moment.logconverge.print.PrintLog;
import com.moment.logconverge.type.LogLevelType;
import com.moment.logconverge.type.ParseType;

/**
 * 日志格式化
 * Created by moment on 2018/1/11.
 */

public class ParseLog {

    /**
     * 格式化入口
     *
     * @param parseType 格式化参数: json,string {@link ParseType}
     * @param levelType 日志级别: 业务日志,错误日志,Action日志,通用信息日志 {@link LogLevelType}
     */
    public static void parse(ParseType parseType, LogLevelType levelType) {
        switch (parseType.getType()) {
            case 0: {
                parseByJson(levelType);
            }
            break;
            case 1: {
                parseByString(levelType);
            }
            break;
        }
    }


    private static void parseByJson(LogLevelType levelType) {
        switch (levelType.getLevel()) {
            case 0:
                parseBusinessLog(true);
                break;
            case 1:
                parseExceptionLog(true);
                break;
            case 2:
                parseActionLog(true);
                break;
            case 3:
                parseCommonLog(true);
                break;
            case 4:
                parseBusinessLog(true);
                parseExceptionLog(true);
                parseActionLog(true);
                parseCommonLog(true);
                break;
        }
    }

    private static void parseByString(LogLevelType levelType) {
        switch (levelType.getLevel()) {
            case 0:
                parseBusinessLog(false);
                break;
            case 1:
                parseExceptionLog(false);
                break;
            case 2:
                parseActionLog(false);
                break;
            case 3:
                parseCommonLog(false);
                break;
            case 4:
                parseBusinessLog(false);
                parseExceptionLog(false);
                parseActionLog(false);
                parseCommonLog(false);
                break;
        }
    }

    /**
     * 通用日志具体格式化方法
     *
     * @param isJson
     */
    private static void parseCommonLog(boolean isJson) {
        CommonLog commonLog = LogProxy.create().getCommonLog();
        if (commonLog != null) {
            if (isJson) {
                PrintLog.print(commonLog.toJson());
            } else {
                PrintLog.print(commonLog.toString());
            }

        }
    }

    /**
     * 业务日志具体格式化方法
     *
     * @param isJson
     */
    private static void parseBusinessLog(boolean isJson) {
        BusinessLog businessLog = LogProxy.create().getBusinessLog();
        if (businessLog != null) {
            if (isJson) {
                PrintLog.print(businessLog.toJson());
            } else {
                PrintLog.print(businessLog.toString());
            }
        }
    }

    /**
     * 错误日志具体格式化方法
     *
     * @param isJson
     */
    private static void parseExceptionLog(boolean isJson) {
        ExceptionLog exceptionLog = LogProxy.create().getExceptionLog();
        if (exceptionLog != null) {
            if (isJson) {
                PrintLog.print(exceptionLog.toJson());
            } else {
                PrintLog.print(exceptionLog.toString());
            }
        }
    }

    /**
     * Action日志具体格式化方法
     *
     * @param isJson
     */
    private static void parseActionLog(boolean isJson) {
        ActionLog actionLog = LogProxy.create().getActionLog();
        if (actionLog != null) {
            if (isJson) {
                PrintLog.print(actionLog.toJson());
            } else {
                PrintLog.print(actionLog.toString());
            }
        }
    }
}
