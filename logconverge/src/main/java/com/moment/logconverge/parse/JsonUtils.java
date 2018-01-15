package com.moment.logconverge.parse;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.moment.logconverge.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by moment on 2018/1/10.
 */

public class JsonUtils {
    public static <M> M parseObj(Class<M> clazz, String data) {
        if (!StringUtils.isEmpty(data)) {
            return JSON.parseObject(data, clazz);
        }
        return null;
    }

    public static <M> List<M> parseArray(Class<M> clazz, String data) {
        if (!StringUtils.isEmpty(data)) {
            return JSON.parseArray(data, clazz);
        }
        return null;
    }

    public static String map2JsonString(Map hashMap) {
        if (!hashMap.isEmpty()) {
            return JSON.toJSONString(hashMap);
        }
        return null;
    }

    /**
     * 根据key获取Json中的value
     *
     * @param jsonString json字符串
     * @param key        键名称
     */
    public static String getJsonValue(String jsonString, String key) {
        if (!TextUtils.isEmpty(jsonString) && !TextUtils.isEmpty(key)) {
            try {
                com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(jsonString);
                if (jsonObject != null && jsonObject.containsKey(key)) {
                    String value = jsonObject.getString(key);
                    if (!TextUtils.isEmpty(value)) {
                        return value;
                    }
                }
            } catch (Exception e) {
                Log.i("JsonUtil", "getJsonValue(), catch Exception, jsonString: " + jsonString + ", key: " + key);
                return null;
            }
        }
        return null;
    }
}
