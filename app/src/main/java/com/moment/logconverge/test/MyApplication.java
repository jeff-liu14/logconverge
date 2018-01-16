package com.moment.logconverge.test;

import android.app.Application;
import android.content.res.Configuration;

import com.moment.logconverge.LogConverge;
import com.moment.logconverge.delegent.LogApplicationProxy;
import com.moment.logconverge.type.CacheType;
import com.moment.logconverge.type.ParseType;
import com.moment.logconverge.type.PrintType;

/**
 * Created by moment on 2018/1/10.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LogConverge.Builder builder = new LogConverge
                .Builder()
                .setLogLevel(LogConverge.ShowLevel.ALL)
                .setChannel("xiaomi")
                .setParseType(ParseType.JSON)
                .setPrintType(PrintType.LOGGCAT)
                .setCacheType(CacheType.DATABASE);
        LogConverge.init(builder, this);
        LogApplicationProxy.getProxy().onCreate(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        LogApplicationProxy.getProxy().onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        LogApplicationProxy.getProxy().onTerminate();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        LogApplicationProxy.getProxy().onTrimMemory(level);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogApplicationProxy.getProxy().onConfigurationChanged(newConfig);
    }
}
