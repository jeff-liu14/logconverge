package com.moment.logconverge.entity;

import com.alibaba.fastjson.JSON;
import com.moment.logconverge.parse.ParseToJson;

/**
 * Created by moment on 2018/1/10.
 */

public class ActionLog implements ParseToJson {

    private String currentPage;
    private String previousPage;
    private long enterTime;
    private long exitTime;
    private String spendTime;

    public String getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(String spendTime) {
        this.spendTime = spendTime;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(String previousPage) {
        this.previousPage = previousPage;
    }

    public long getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(long enterTime) {
        this.enterTime = enterTime;
    }

    public long getExitTime() {
        return exitTime;
    }

    public void setExitTime(long exitTime) {
        this.exitTime = exitTime;
    }

    @Override
    public String toJson() {
        String json = JSON.toJSONString(this);
        return (json == null || json.equals("{}")) ? "{\"error\":\"json parse error\"}" : JSON.toJSONString(this);
    }

    @Override
    public String toString() {
        return "UserLog{" +
                "currentPage='" + currentPage + '\'' +
                ", previousPage='" + previousPage + '\'' +
                ", enterTime=" + enterTime +
                ", exitTime=" + exitTime +
                ", spendTime='" + spendTime + '\'' +
                '}';
    }
}
