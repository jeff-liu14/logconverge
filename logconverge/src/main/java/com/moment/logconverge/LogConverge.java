package com.moment.logconverge;

import android.app.Application;

import com.moment.logconverge.cache.CacheRule;
import com.moment.logconverge.cache.NetCacheUtil;
import com.moment.logconverge.cache.database.DataBaseCache;
import com.moment.logconverge.cache.file.FileCache;
import com.moment.logconverge.cache.util.LogToFile;
import com.moment.logconverge.delegent.LogProxy;
import com.moment.logconverge.entity.BusinessLog;
import com.moment.logconverge.entity.ExceptionLog;
import com.moment.logconverge.parse.ParseLog;
import com.moment.logconverge.parse.ParseRule;
import com.moment.logconverge.print.PrintRule;
import com.moment.logconverge.type.CacheType;
import com.moment.logconverge.type.LogLevelType;
import com.moment.logconverge.type.ParseType;
import com.moment.logconverge.type.PrintType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by moment on 2018/1/10.
 */

public class LogConverge {

    public static final String TAG = "LogConverge";

    public static Application application;
    private static LogConverge logConverge;

    //初始化
    public static void init(Builder builder, Application app) {
        application = app;
        LogToFile.init(app);
        synchronized (LogConverge.class) {
            if (logConverge == null) {
                logConverge = builder.create();
            }
        }
    }

    public static LogConverge create() {
        if (logConverge == null) {
            Builder builder = new Builder()
                    .setChannel("")
                    .setLogLevel()
                    .setParseType(ParseType.JSON)
                    .setPrintType(PrintType.LOGGCAT)
                    .setCacheType(CacheType.FILE);
            logConverge = builder.create();
        }
        return logConverge;

    }

    public List<String> getAllLogs() {
        List<String> strings = new ArrayList<>();
        if (CacheRule.getDefaultRule().getType() == CacheType.FILE.getType()) {
            strings = FileCache.init().getAllLogs();
        } else if (CacheRule.getDefaultRule().getType() == CacheType.DATABASE.getType()) {
            strings = DataBaseCache.init().getAllLogs();
        }
        return strings;
    }

    public void clearAllLog() {
        if (CacheRule.getDefaultRule().getType() == CacheType.FILE.getType()) {
            FileCache.init().deleteAll();
        } else if (CacheRule.getDefaultRule().getType() == CacheType.DATABASE.getType()) {
            DataBaseCache.init().deleteAll();
        }
    }

    /**
     * @return jsonArray
     */
    public String getUploadLog() {
        return NetCacheUtil.getlog();
    }

    public void onUploadSucc(boolean succ) {
        NetCacheUtil.onUpLoaded(succ);
    }

    //业务日志
    public void log(Map<String, Object> log) {
        BusinessLog businessLog = new BusinessLog();
        businessLog.setBusinessLog(log);
        LogProxy.create().setBusinessLog(businessLog);
        ParseLog.parse(ParseRule.getDefaultRule(), LogLevelType.BUSINESS);
    }

    public void logNetError(String log) {
        Map<String, Object> map = new HashMap<>();
        map.put("netError", log);
        ExceptionLog exceptionLog = new ExceptionLog();
        exceptionLog.setError(map);
        LogProxy.create().setExceptionLog(exceptionLog);
        ParseLog.parse(ParseRule.getDefaultRule(), LogLevelType.EXCEPTION);
    }

    public enum ShowLevel {
        ACTION(0),
        EXCEPTION(1),
        ALL(2),
        NONE(3);
        int type;

        ShowLevel(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

    }

    public static class Builder {
        private String channel = "";
        private PrintType printType = PrintType.LOGGCAT;
        private ParseType parseType = ParseType.JSON;
        private CacheType cacheType = CacheType.FILE;
        private List<LogLevelType> logLevelTypes = new ArrayList<>();

        public Builder() {

        }

        public Builder setChannel(String channel) {
            this.channel = channel;
            return this;
        }

        public Builder setPrintType(PrintType printType) {
            this.printType = printType;
            return this;
        }

        public Builder setParseType(ParseType parseType) {
            this.parseType = parseType;
            return this;
        }

        public Builder setCacheType(CacheType cacheType) {
            this.cacheType = cacheType;
            return this;
        }

        public Builder setLogLevel(ShowLevel... showLevels) {
            if (showLevels != null) {
                for (ShowLevel level :
                        showLevels) {
                    switch (level) {
                        case ACTION: {
                            if (!logLevelTypes.contains(LogLevelType.COMMON)) {
                                logLevelTypes.add(LogLevelType.COMMON);
                            }
                            if (!logLevelTypes.contains(LogLevelType.ACTION)) {
                                logLevelTypes.add(LogLevelType.ACTION);
                            }
                        }

                        break;
                        case EXCEPTION: {
                            if (!logLevelTypes.contains(LogLevelType.COMMON)) {
                                logLevelTypes.add(LogLevelType.COMMON);
                            }
                            if (!logLevelTypes.contains(LogLevelType.EXCEPTION)) {
                                logLevelTypes.add(LogLevelType.EXCEPTION);
                            }
                        }
                        break;
                        case ALL: {
                            if (!logLevelTypes.contains(LogLevelType.COMMON)) {
                                logLevelTypes.add(LogLevelType.COMMON);
                            }
                            if (!logLevelTypes.contains(LogLevelType.BUSINESS)) {
                                logLevelTypes.add(LogLevelType.BUSINESS);
                            }
                            if (!logLevelTypes.contains(LogLevelType.ACTION)) {
                                logLevelTypes.add(LogLevelType.ACTION);
                            }
                            if (!logLevelTypes.contains(LogLevelType.EXCEPTION)) {
                                logLevelTypes.add(LogLevelType.EXCEPTION);
                            }
                        }
                        break;
                        case NONE: {

                        }
                    }
                }
            }
            return this;
        }


        private LogConverge create() {
            LogProxy.create()
                    .registerActivityLifecycleCallbacks(application)
                    .initCommonLog(application, channel)
                    .setLogLevelTypes(logLevelTypes);
//                    .registerUnCatchException();
            PrintRule.setDefaultRule(printType);
            CacheRule.setDefaultRule(cacheType);
            ParseRule.setDefaultRule(parseType);
            return new LogConverge();
        }
    }
}
