package com.moment.logconverge.cache.util;

import com.moment.logconverge.cache.CacheRule;
import com.moment.logconverge.cache.database.DataBaseCache;
import com.moment.logconverge.cache.file.FileCache;
import com.moment.logconverge.type.CacheType;

/**
 * Created by moment on 2018/1/15.
 */

public class CacheUtil {

    public static void cache(String log) {
        CacheType cacheType = CacheRule.getDefaultRule();
        switch (cacheType) {
            case DATABASE: {
                DataBaseCache.init().save(log);
            }
            break;
            case FILE: {
                FileCache.init().save(log);
            }
            break;
            case NET:
                break;

        }
    }
}
