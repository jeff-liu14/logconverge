package com.moment.logconverge.delegent;

import android.app.Application;
import android.content.Context;

import com.moment.logconverge.entity.BusinessLog;
import com.moment.logconverge.entity.CommonLog;
import com.moment.logconverge.entity.ExceptionLog;
import com.moment.logconverge.entity.ActionLog;

/**
 * Created by moment on 2018/1/10.
 */

public interface LifeCircleProxy {

    LogProxy initCommonLog(Context context, String channel);

    void onFragmentHiddenChanged(boolean hidden, String name);

    void onFragmentResume();

    void onFragmentPause();

    LogProxy registerActivityLifecycleCallbacks(Application application);

    LogProxy registerUnCatchException();

    BusinessLog getBusinessLog();

    CommonLog getCommonLog();

    ExceptionLog getExceptionLog();

    ActionLog getActionLog();
}
