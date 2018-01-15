package com.moment.logconverge.cache;

import com.alibaba.fastjson.JSON;
import com.moment.logconverge.cache.database.DataBaseCache;
import com.moment.logconverge.cache.file.FileCache;
import com.moment.logconverge.type.CacheType;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by moment on 2018/1/15.
 */

public class NetCacheUtil {

    public static String getlog() {
        if (CacheRule.getDefaultRule().getType() == CacheType.FILE.getType()) {
            String log = JSON.toJSONString(FileCache.init().getAllLogs());
            return log;
        } else if (CacheRule.getDefaultRule().getType() == CacheType.DATABASE.getType()) {
            String log = JSON.toJSONString(DataBaseCache.init().getAllLogs());
            return log;
        }
        return null;
    }

    public static void onUpLoaded(boolean success) {
        if (success) {
            if (CacheRule.getDefaultRule().getType() == CacheType.FILE.getType()) {
                FileCache.init().deleteAll();
            } else if (CacheRule.getDefaultRule().getType() == CacheType.DATABASE.getType()) {
                DataBaseCache.init().deleteAll();
            }
        }
    }
}
