package com.moment.logconverge.type;

/**
 * 日志级别
 * Created by moment on 2018/1/10.
 */

public enum LogLevelType {

    BUSINESS(0),
    EXCEPTION(1),
    ACTION(2),
    COMMON(3),
    NONE(4),
    ALL(5);
    public int level;

    LogLevelType(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }
}
