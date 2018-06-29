package com.moment.logconverge.entity;

import org.litepal.crud.DataSupport;

/**
 * Created by moment on 2018/1/15.
 */

public class LogEntity extends DataSupport {

    private String name;

    private String log;

    private int type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
