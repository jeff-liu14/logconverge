package com.moment.logconverge.type;

/**
 * 解析规则
 * Created by moment on 2018/1/10.
 */

public enum ParseType {

    JSON(0),
    STRING(1);

    public int type;

    ParseType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }
}
