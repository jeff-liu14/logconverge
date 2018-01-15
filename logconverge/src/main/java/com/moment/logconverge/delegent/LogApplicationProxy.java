package com.moment.logconverge.delegent;

import android.app.Application;
import android.content.res.Configuration;

import org.litepal.LitePal;

/**
 * Created by moment on 2018/1/15.
 */

public class LogApplicationProxy implements LifeCall {
    private static LogApplicationProxy proxy;
    private static Application application;

    public static LogApplicationProxy getProxy() {
        synchronized (LogApplicationProxy.class) {
            if (proxy == null) {
                proxy = new LogApplicationProxy();
            }
        }
        return proxy;
    }

    public static Application getApplication() {
        return application;
    }

    @Override
    public void onCreate(Application application) {
        LogApplicationProxy.application = application;
        LitePal.initialize(application);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

    }

    @Override
    public void onTrimMemory(int level) {

    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void onTerminate() {

    }
}
