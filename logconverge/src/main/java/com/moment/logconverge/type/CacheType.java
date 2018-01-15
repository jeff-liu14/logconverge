package com.moment.logconverge.type;

/**
 * 缓存方法
 * Created by moment on 2018/1/10.
 */

public enum CacheType {

    NET(0),
    DATABASE(1),
    FILE(2);

    public int type;

    CacheType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }
}
