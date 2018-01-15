package com.moment.logconverge.entity;

import com.alibaba.fastjson.JSON;
import com.moment.logconverge.parse.ParseToJson;

/**
 * Created by moment on 2018/1/10.
 */

public class CommonLog implements ParseToJson {
    private String devicebrand;
    private String sysversion;
    private String uuid;
    private String appversion;
    private String channel;
    private String memorysize;

    public String getDeviceBrand() {
        return devicebrand;
    }

    public void setDeviceBrand(String devicebrand) {
        this.devicebrand = devicebrand;
    }

    public String getSysversion() {
        return sysversion;
    }

    public void setSysversion(String sysversion) {
        this.sysversion = sysversion;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getMemorysize() {
        return memorysize;
    }

    public void setMemorysize(String memorysize) {
        this.memorysize = memorysize;
    }

    @Override
    public String toJson() {
        String json = JSON.toJSONString(this);
        return (json == null || json.equals("{}")) ? "{\"error\":\"json parse error\"}" : JSON.toJSONString(this);
    }

    @Override
    public String toString() {
        return "CommonLog{" +
                "devicebrand='" + devicebrand + '\'' +
                ", sysversion='" + sysversion + '\'' +
                ", uuid='" + uuid + '\'' +
                ", appversion='" + appversion + '\'' +
                ", channel='" + channel + '\'' +
                ", memorysize='" + memorysize + '\'' +
                '}';
    }
}
