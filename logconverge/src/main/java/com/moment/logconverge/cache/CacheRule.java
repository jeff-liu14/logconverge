package com.moment.logconverge.cache;

import com.moment.logconverge.type.CacheType;

/**
 * 本地缓存规则
 * Created by moment on 2018/1/10.
 */

public class CacheRule {

    //默认文件缓存日志
    private static CacheType defaultRule = CacheType.FILE;

    public static CacheType getDefaultRule() {
        return defaultRule;
    }

    public static void setDefaultRule(CacheType cacheType) {
        defaultRule = cacheType;
    }
}
