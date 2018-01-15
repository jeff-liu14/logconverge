package com.moment.logconverge.type;

/**
 * 打印
 * Created by moment on 2018/1/10.
 */

public enum PrintType {

    LOGGCAT(0),
    TOASE(1);
    public int type;

    PrintType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }
}
