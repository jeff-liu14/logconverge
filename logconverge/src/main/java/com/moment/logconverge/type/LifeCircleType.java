package com.moment.logconverge.type;

/**
 * Created by moment on 2018/1/10.
 */

public enum LifeCircleType {

    ACTIVITY(0),
    FRAGMENT(1);

    public int type;

    LifeCircleType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }
}
