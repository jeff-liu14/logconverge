package com.moment.logconverge.cache.file;

import com.alibaba.fastjson.JSON;
import com.moment.logconverge.LogConverge;
import com.moment.logconverge.cache.util.LogToFile;
import com.moment.logconverge.util.StringUtils;
import com.moment.logconverge.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moment on 2018/1/10.
 */

public class FileCache {
    private static String cacheDir = "";
    private static String sdPath = "";
    private static String cache_key = "CACHE_KEY";
    private static List<String> keyList = new ArrayList<>();
    private static FileCache fileCache;

    public static FileCache init() {
        synchronized (FileCache.class) {
            if (fileCache == null) {
                fileCache = new FileCache();
            }
        }
        return fileCache;
    }

    public FileCache save(String log) {
        String time = System.currentTimeMillis() + "";
        String keylog = log + time;
        if (LogToFile.log(String.valueOf(keylog.hashCode()), log)) {
            keyList.add(String.valueOf(log.hashCode()));
            try {
                if (keyList != null && keyList.size() > 0) {
                    String key = JSON.toJSONString(keyList);
                    if (!StringUtils.isEmpty(key)) {
                        Utils.getmACache(LogConverge.application).put(cache_key, key);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return this;
    }

    public FileCache delete(String key) {
        LogToFile.deleteFileByKey(key);
        return this;
    }

    public FileCache delete(List<String> key) {
        if (key != null && key.size() > 0) {
            for (String str : key) {
                delete(str);
            }
        }
        return this;
    }

    public FileCache delete(String... key) {
        if (key.length > 0) {
            for (String str : key) {
                delete(str);
            }
        }
        return this;
    }

    public FileCache deleteAll() {
        LogToFile.deleteAllLogs();
        return this;
    }

    public List<String> getKeyList() {
        String json = Utils.getmACache(LogConverge.application).getAsString(cache_key);
        List<String> strings = JSON.parseArray(json, String.class);
        return strings;
    }

    public List<String> getAllLogs() {
        return LogToFile.getLogFileStr();
    }

    public List<String> getAllFileName() {
        return LogToFile.getAllFileName();
    }
}
