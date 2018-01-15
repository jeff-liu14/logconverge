package com.example.net.entity;

/**
 * Created by moment on 2017/11/27.
 */

public class Result<T> {

    private int code;// 状态码

    private long saveTime;// 状态码

    private T data;// object或array

    private Meta meta;// 额外信息，比如token，耗时情况等

    private String message = "";// 提示消息

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(long saveTime) {
        this.saveTime = saveTime;
    }
}
