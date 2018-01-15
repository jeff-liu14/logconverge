package com.moment.logconverge.delegent;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

import com.moment.logconverge.entity.BusinessLog;
import com.moment.logconverge.entity.CommonLog;
import com.moment.logconverge.entity.ExceptionLog;
import com.moment.logconverge.entity.ActionLog;
import com.moment.logconverge.parse.ParseLog;
import com.moment.logconverge.parse.ParseRule;
import com.moment.logconverge.type.LogLevelType;
import com.moment.logconverge.util.AppUtils;
import com.moment.logconverge.util.Cockroach;
import com.moment.logconverge.util.StringUtils;
import com.moment.logconverge.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by moment on 2018/1/10.
 */


public class LogProxy implements LifeCircleProxy {
    private static LogProxy logProxy;
    private static String currentActivity;
    private static CommonLog commonLog;
    private static BusinessLog businessLog;
    private static ExceptionLog exceptionLog;
    private static ActionLog actionLog;
    private static String previousPage;
    private static String currentPage;
    private static long enterTime;
    private static long exitTime;
    private static boolean hasFragment;
    private static List<LogLevelType> logLevelTypes = new ArrayList<>();

    public static LogProxy create() {
        synchronized (LogProxy.class) {
            if (logProxy == null) {
                logProxy = new LogProxy();
            }
        }
        return logProxy;
    }

    /**
     * 初始化通用日志
     *
     * @param context
     * @param channel
     * @return
     */
    @Override
    public LogProxy initCommonLog(Context context, String channel) {
        commonLog = new CommonLog();
        commonLog.setAppversion(AppUtils.getAppVersionName(context));
        commonLog.setChannel(channel);
        commonLog.setSysversion(AppUtils.getSystemVersion());
        commonLog.setDeviceBrand(AppUtils.getDeviceBrand());
        commonLog.setMemorysize(String.valueOf(AppUtils.getMemorySize(context)));
        commonLog.setUuid(AppUtils.getUUID(context));
        return this;
    }

    /**
     * 获取Action日志相关参数
     *
     * @param hidden
     * @param name
     */
    @Override
    public void onFragmentHiddenChanged(boolean hidden, String name) {
        hasFragment = true;
        if (!hidden) {
            if (StringUtils.isEmpty(previousPage)) {
                currentPage = currentActivity + "_" + name;
            } else {
                currentPage = currentActivity + "_" + name;
            }
            if (logLevelTypes.contains(LogLevelType.ACTION)) {
                ParseLog.parse(ParseRule.getDefaultRule(), LogLevelType.ACTION);
            }
            enterTime = System.currentTimeMillis();
        } else {
            exitTime = System.currentTimeMillis();
            previousPage = currentActivity + "_" + name;
        }

    }

    public LogProxy setLogLevelTypes(List<LogLevelType> logLevelType) {
        logLevelTypes = logLevelType;
        return this;
    }

    /**
     * 获取Action日志相关参数
     */
    @Override
    public void onFragmentResume() {
        hasFragment = true;
        enterTime = System.currentTimeMillis();
    }

    /**
     * 获取Action日志相关参数
     */
    @Override
    public void onFragmentPause() {
        hasFragment = true;
        exitTime = System.currentTimeMillis();
    }

    /**
     * 获取Action日志相关参数
     *
     * @param application
     * @return
     */
    @Override
    public LogProxy registerActivityLifecycleCallbacks(Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                currentActivity = activity.getClass().getSimpleName();
                if (activity instanceof AppCompatActivity) {
                    AppCompatActivity compatActivity = (AppCompatActivity) activity;
                    List<android.support.v4.app.Fragment> fragments = compatActivity.getSupportFragmentManager().getFragments();
                    if (fragments != null && fragments.size() > 0) {
                        String currentActivity = fragments.get(0).getActivity().getClass().getSimpleName();
                        String currentFragment = fragments.get(0).getClass().getSimpleName();
                        String name = currentActivity + "_" + currentFragment;
                        if (StringUtils.isEmpty(previousPage)) {
                            currentPage = name;
                            if (logLevelTypes.contains(LogLevelType.ACTION)) {
                                ParseLog.parse(ParseRule.getDefaultRule(), LogLevelType.ACTION);
                            }
                        } else {
                            if (!previousPage.equals(name)) {
                                currentPage = name;
                                if (logLevelTypes.contains(LogLevelType.ACTION)) {
                                    ParseLog.parse(ParseRule.getDefaultRule(), LogLevelType.ACTION);
                                }
                            }
                        }
                        previousPage = name;

                    } else {
                        hasFragment = false;
                        String currentActivity = compatActivity.getClass().getSimpleName();
                        String name = currentActivity;

                        if (StringUtils.isEmpty(previousPage)) {
                            currentPage = name;
                            if (logLevelTypes.contains(LogLevelType.ACTION)) {
                                ParseLog.parse(ParseRule.getDefaultRule(), LogLevelType.ACTION);
                            }
                        } else {
                            if (!previousPage.equals(name)) {
                                currentPage = name;
                                if (logLevelTypes.contains(LogLevelType.ACTION)) {
                                    ParseLog.parse(ParseRule.getDefaultRule(), LogLevelType.ACTION);
                                }
                            }
                        }
                        previousPage = name;
                        enterTime = System.currentTimeMillis();
                    }

                }
            }

            @Override
            public void onActivityPaused(Activity activity) {
                if (!hasFragment) {
                    exitTime = System.currentTimeMillis();
                }
            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
        return this;
    }

    /**
     * 获取异常数据
     *
     * @return
     */
    @Override
    public LogProxy registerUnCatchException() {
        Cockroach.install(new Cockroach.ExceptionHandler() {
            @Override
            public void handlerException(final Thread thread, final Throwable throwable) {

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        ExceptionLog exceptionLog = new ExceptionLog();
                        Map<String, Object> map = new HashMap<>();
                        map.put("clientError", throwable.getMessage() + " case:" + throwable.toString());
                        exceptionLog.setError(map);
                        setExceptionLog(exceptionLog);
                        if (logLevelTypes.contains(LogLevelType.EXCEPTION)) {
                            ParseLog.parse(ParseRule.getDefaultRule(), LogLevelType.EXCEPTION);
                        }
                    }
                });
            }
        });
        return this;
    }


    /**
     * 获取业务日志信息
     *
     * @return
     */
    @Override
    public BusinessLog getBusinessLog() {
        if (logLevelTypes.contains(LogLevelType.COMMON)) {
            ParseLog.parse(ParseRule.getDefaultRule(), LogLevelType.COMMON);
        }
        return businessLog;
    }

    public void setBusinessLog(BusinessLog business) {
        businessLog = business;
    }


    @Override
    public CommonLog getCommonLog() {
        return commonLog;
    }

    @Override
    public ExceptionLog getExceptionLog() {
        if (logLevelTypes.contains(LogLevelType.COMMON)) {
            ParseLog.parse(ParseRule.getDefaultRule(), LogLevelType.COMMON);
        }
        return exceptionLog;
    }

    public void setExceptionLog(ExceptionLog exception) {
        exceptionLog = exception;
    }

    /**
     * 返回Action日志信息
     *
     * @return
     */
    @Override
    public ActionLog getActionLog() {
        if (logLevelTypes.contains(LogLevelType.COMMON)) {
            ParseLog.parse(ParseRule.getDefaultRule(), LogLevelType.COMMON);
        }
        actionLog = new ActionLog();
        actionLog.setCurrentPage(currentPage + "");
        actionLog.setPreviousPage(previousPage + "");
        actionLog.setEnterTime(enterTime);
        actionLog.setExitTime(exitTime);
        actionLog.setSpendTime(Utils.getTimeExpend(enterTime, exitTime));
        return actionLog;
    }
}
