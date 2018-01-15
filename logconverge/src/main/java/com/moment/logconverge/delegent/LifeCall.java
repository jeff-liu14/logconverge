package com.moment.logconverge.delegent;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by moment on 2018/1/15.
 */

public interface LifeCall {
    void onCreate(Application application);

    void onConfigurationChanged(Configuration newConfig);

    void onTrimMemory(int level);

    void onLowMemory();

    void onTerminate();

}
