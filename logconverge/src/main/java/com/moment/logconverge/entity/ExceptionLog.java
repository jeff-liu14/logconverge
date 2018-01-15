package com.moment.logconverge.entity;

import com.alibaba.fastjson.JSON;
import com.moment.logconverge.parse.ParseToJson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by moment on 2018/1/10.
 */

public class ExceptionLog implements ParseToJson {

    private Map<String, Object> error = new HashMap<>();

    public Map<String, Object> getError() {
        return error;
    }

    public void setError(Map<String, Object> error) {
        this.error = error;
    }

    @Override
    public String toJson() {
        String json = JSON.toJSONString(this);
        return (json == null || json.equals("{}")) ? "{\"error\":\"json parse error\"}" : JSON.toJSONString(this);
    }

    @Override
    public String toString() {
        StringBuilder client = new StringBuilder();
        client.append("{");
        for (Map.Entry<String, Object> entry : error.entrySet()) {
            client.append("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        client.append("}");

        return "ExceptionLog{" +
                client.toString() +
                '}';
    }
}
