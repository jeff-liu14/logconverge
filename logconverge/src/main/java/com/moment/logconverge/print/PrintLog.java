package com.moment.logconverge.print;

import android.util.Log;
import android.widget.Toast;

import com.moment.logconverge.LogConverge;
import com.moment.logconverge.cache.util.CacheUtil;
import com.moment.logconverge.type.PrintType;

/**
 * 打印日志具体实现类
 * Created by moment on 2018/1/11.
 */

public class PrintLog {

    /**
     * 打印日志主方法
     */
    public static void print(String log) {
        print(PrintRule.getDefaultRule(), log);
    }

    private static void print(PrintType printType, String log) {
        switch (printType.getType()) {
            case 0:
                logcat(log);
                break;
            case 1:
                toast(log);
                break;
        }
    }

    /**
     * 控制台打印
     */
    private static void logcat(final String log) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(LogConverge.TAG, log);
                CacheUtil.cache(log);
            }
        }).start();
    }

    /**
     * 吐司打印
     */
    private static void toast(final String log) {
        if (LogConverge.application != null) {
            Toast.makeText(LogConverge.application, log, Toast.LENGTH_SHORT).show();
        } else {
            logcat(log);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                CacheUtil.cache(log);
            }
        }).start();
    }
}
