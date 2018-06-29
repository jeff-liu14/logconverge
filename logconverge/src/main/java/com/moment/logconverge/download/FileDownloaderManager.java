package com.moment.logconverge.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;

/**
 * Created by moment on 2018/1/29.
 */

public class FileDownloaderManager {
    private static LocalBroadcastManager mLocalBroadcastManager;
    private static MyBroadcastReceiver mBroadcastReceiver;
    private static DownloadCallback downloadCallback;
    private static Context mContext;
    public final static String ACTION_TYPE_PREPARE = "action.type.prepare";
    public final static String ACTION_TYPE_PROGRESS = "action.type.progress";
    public final static String ACTION_TYPE_COMPLETE = "action.type.complete";
    public final static String ACTION_TYPE_FAIL = "action.type.fail";

    /**
     * 定义一下回调方法
     */
    public interface DownloadCallback {
        void onPrepare();

        void onProgress(int progress);

        void onComplete(File file);

        void onFail(String msg);
    }

    public static void init(Context context) {
        mContext = context;
    }

    public static void registerDownload(DownloadCallback callback) throws Exception {
        if (mContext == null) throw new Exception("先调用init方法进行初始化");
        downloadCallback = callback;
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(mContext);
        mBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_TYPE_PREPARE);
        intentFilter.addAction(ACTION_TYPE_PROGRESS);
        intentFilter.addAction(ACTION_TYPE_COMPLETE);
        intentFilter.addAction(ACTION_TYPE_FAIL);
        mLocalBroadcastManager.registerReceiver(mBroadcastReceiver, intentFilter);
    }

    public static void download(String url) throws Exception {
        if (mBroadcastReceiver == null || mBroadcastReceiver == null)
            throw new Exception("先调用registerDownload方法进行初始化");
        if (mContext == null) throw new Exception("先调用init方法进行初始化");
        Intent intent = new Intent(mContext, DownloadService.class);
        intent.putExtra("url", url);
        mContext.startService(intent);
    }

    public static void unbinder() {
        if (mLocalBroadcastManager != null) {
            mLocalBroadcastManager.unregisterReceiver(mBroadcastReceiver);
        }

        mBroadcastReceiver = null;
        mLocalBroadcastManager = null;
        downloadCallback = null;
        mContext = null;
    }

    public static class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case ACTION_TYPE_PREPARE:
                    if (downloadCallback != null) {
                        downloadCallback.onPrepare();
                    }
                    break;
                case ACTION_TYPE_PROGRESS:
                    int progress = intent.getIntExtra("progress", 0);
//                    Log.d("progress", "|- " + progress + " -|");
                    if (downloadCallback != null) {
                        downloadCallback.onProgress(progress);
                    }
                    break;
                case ACTION_TYPE_COMPLETE:
                    String file_path = intent.getStringExtra("file_path");
                    if (!TextUtils.isEmpty(file_path)) {
                        File file = new File(file_path);
                        if (file.exists()) {
                            if (downloadCallback != null) {
                                downloadCallback.onComplete(file);
                            }
                        }
                    }
                    break;
                case ACTION_TYPE_FAIL:
                    String error = intent.getStringExtra("error");
                    if (downloadCallback != null) {
                        downloadCallback.onFail(error + "");
                    }
                    break;
            }
        }
    }

}
