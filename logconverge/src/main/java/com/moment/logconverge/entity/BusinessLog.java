package com.moment.logconverge.entity;

import com.alibaba.fastjson.JSON;
import com.moment.logconverge.parse.ParseToJson;

import java.util.Map;

/**
 * Created by moment on 2018/1/10.
 */

public class BusinessLog implements ParseToJson {
    // 业务日志类型
    private Map<String, Object> businessLog;

    public Map<String, Object> getBusinessLog() {
        return businessLog;
    }

    public void setBusinessLog(Map<String, Object> businessLog) {
        this.businessLog = businessLog;
    }

    @Override
    public String toJson() {
        String json = JSON.toJSONString(this);
        return (json == null || json.equals("{}")) ? "{\"error\":\"json parse error\"}" : JSON.toJSONString(this);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        for (Map.Entry<String, Object> entry : businessLog.entrySet()) {
            builder.append("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        builder.append("}");
        return "BusinessLog{" +
                "commonLog=" + builder.toString() +
                '}';
    }
}
