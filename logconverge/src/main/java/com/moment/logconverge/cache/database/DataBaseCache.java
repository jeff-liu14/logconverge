package com.moment.logconverge.cache.database;

import com.moment.logconverge.entity.LogEntity;
import com.moment.logconverge.type.LogLevelType;
import com.moment.logconverge.util.MD5Util;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by moment on 2018/1/10.
 */

public class DataBaseCache {


    public static DataBaseCache init() {
        return new DataBaseCache();
    }


    public DataBaseCache save(String log) {
        String time = System.currentTimeMillis() + "";
        String key = time + log;
        String name = MD5Util.encrypt(key);
        LogEntity entity = new LogEntity();
        entity.setLog(log);
        entity.setName(name);
        entity.setType(LogLevelType.ALL.getLevel());
        entity.save();
        return this;
    }


    public DataBaseCache delete(String key) {
        DataSupport.deleteAll(LogEntity.class, "name = ?", key);
        return this;
    }


    public DataBaseCache delete(List<String> key) {
        if (key != null && key.size() > 0) {
            for (String str : key) {
                delete(str);
            }
        }
        return this;
    }


    public DataBaseCache delete(String... key) {
        if (key != null && key.length > 0) {
            for (String str : key) {
                delete(str);
            }
        }
        return this;
    }

    public DataBaseCache deleteAll() {
        DataSupport.deleteAll(LogEntity.class);
        return this;
    }

    public List<String> getKeyList() {
        List<LogEntity> log = DataSupport.findAll(LogEntity.class);
        List<String> strings = new ArrayList<>();
        if (log != null && log.size() > 0) {
            for (LogEntity entity : log) {
                strings.add(entity.getName());
            }
            return strings;
        }
        return strings;
    }

    public List<String> getAllLogs() {
        List<LogEntity> log = DataSupport.findAll(LogEntity.class);
        List<String> strings = new ArrayList<>();
        if (log != null && log.size() > 0) {
            for (LogEntity entity : log) {
                strings.add(entity.getLog());
            }
            return strings;
        }
        return strings;
    }
}
