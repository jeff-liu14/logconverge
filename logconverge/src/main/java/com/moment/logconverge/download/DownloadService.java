package com.moment.logconverge.download;

import android.app.ActivityManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.NotificationCompat;
import android.text.TextUtils;

import com.moment.logconverge.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by moment on 2018/1/29.
 */

public class DownloadService extends IntentService {
    private static String TAG = "DownloadService";

    //定义notify的id，避免与其它的notification的处理冲突
    private static final int NOTIFY_ID = 0;
    private static final String CHANNEL = "update";

    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;
    private Notification.Builder _mBuilder;
    //    private DownloadCallback callback;
    private LocalBroadcastManager mLocalBroadcastManager;
    //定义个更新速率，避免更新通知栏过于频繁导致卡顿
    private float rate = .0f;

    public DownloadService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    private void prepare() {
        Intent intent = new Intent(FileDownloaderManager.ACTION_TYPE_PREPARE);
        mLocalBroadcastManager.sendBroadcast(intent);
    }

    // 发送线程状态信息
    private void progress(int progress) {
        Intent intent = new Intent(FileDownloaderManager.ACTION_TYPE_PROGRESS);
        intent.putExtra("progress", progress);
        mLocalBroadcastManager.sendBroadcast(intent);
    }

    private void complete(File file) {
        Intent intent = new Intent(FileDownloaderManager.ACTION_TYPE_COMPLETE);
        intent.putExtra("file_path", file.getAbsolutePath());
        mLocalBroadcastManager.sendBroadcast(intent);
    }

    private void fail(String value) {
        Intent intent = new Intent(FileDownloaderManager.ACTION_TYPE_FAIL);
        intent.putExtra("error", value);
        mLocalBroadcastManager.sendBroadcast(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String url = intent.getStringExtra("url");
        downApk(url);
    }

    private static String NOTIFICATION_CHANNEL_ID = "download_apk";
    private static String NOTIFICATION_CHANNEL_NAME = "download";

    /**
     * 创建通知栏
     */
    private void setNotification() {
        if (mNotificationManager == null) {
            mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (android.os.Build.VERSION.SDK_INT >= 26) {
                int importance = NotificationManager.IMPORTANCE_LOW;
                NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, importance);
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.enableVibration(true);
                notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                _mBuilder = new Notification.Builder(this, NOTIFICATION_CHANNEL_ID);
                _mBuilder.setContentTitle("开始下载")
                        .setContentText("更新中...")
                        .setOngoing(true)
                        .setAutoCancel(true)
                        .setWhen(System.currentTimeMillis());
                mNotificationManager.createNotificationChannel(notificationChannel);
            } else {
                mBuilder = new NotificationCompat.Builder(this);
                mBuilder.setContentTitle("开始下载")
                        .setContentText("更新中...")
                        .setOngoing(true)
                        .setAutoCancel(true)
                        .setWhen(System.currentTimeMillis());
            }


        }

        if (android.os.Build.VERSION.SDK_INT >= 26) {
            mNotificationManager.notify(NOTIFY_ID, _mBuilder.build());
        } else {
            mNotificationManager.notify(NOTIFY_ID, mBuilder.build());
        }
    }

    /**
     * 下载完成
     */
    private void complete(String msg) {
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            if (_mBuilder != null) {
                _mBuilder.setContentTitle("新版本").setContentText(msg);
                Notification notification = _mBuilder.build();
                notification.flags = Notification.FLAG_AUTO_CANCEL;
                mNotificationManager.notify(NOTIFY_ID, notification);
            }
        } else {
            if (mBuilder != null) {
                mBuilder.setContentTitle("新版本").setContentText(msg);
                Notification notification = mBuilder.build();
                notification.flags = Notification.FLAG_AUTO_CANCEL;
                mNotificationManager.notify(NOTIFY_ID, notification);
            }
        }

        stopSelf();
    }

    /**
     * 开始下载apk
     */
    public void downApk(String url) {
        if (TextUtils.isEmpty(url)) {
            complete("下载路径错误");
            return;
        }
        setNotification();
        handler.sendEmptyMessage(0);


        Request request = new Request.Builder().url(url).build();
        try {
            Response response = new OkHttpClient().newCall(request).execute();
            if (response.body() == null) {
                Message message = Message.obtain();
                message.what = 1;
                message.obj = "下载错误";
                handler.sendMessage(message);
                return;
            }
            InputStream is = null;
            byte[] buff = new byte[2048];
            int len;
            FileOutputStream fos = null;
            try {
                is = response.body().byteStream();
                long total = response.body().contentLength();
                File file = createFile();
                fos = new FileOutputStream(file);
                long sum = 0;
                while ((len = is.read(buff)) != -1) {
                    fos.write(buff, 0, len);
                    sum += len;
                    int progress = (int) (sum * 1.0f / total * 100);
                    if (rate != progress) {
                        Message message = Message.obtain();
                        message.what = 2;
                        message.obj = progress;
                        handler.sendMessage(message);
                        rate = progress;
                    }
                }
                fos.flush();
                Message message = Message.obtain();
                message.what = 3;
                message.obj = file.getAbsoluteFile();
                handler.sendMessage(message);
                notifyInstall(file.getAbsoluteFile());
            } catch (Exception e) {
                e.printStackTrace();
                Message message = Message.obtain();
                message.what = 4;
                handler.sendMessage(message);
            } finally {
                try {
                    if (is != null)
                        is.close();
                    if (fos != null)
                        fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            Message message = Message.obtain();
            message.what = 1;
            message.obj = e.getMessage();
            handler.sendMessage(message);
        }
    }

    private void notifyInstall(File file) {
        if (onFront()) {
            Intent intent = installIntent(file);
            startActivity(intent);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= 26) {
                Intent intent = installIntent(file.getAbsoluteFile());
                PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext()
                        , 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                _mBuilder.setContentIntent(pIntent)
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText("下载完成，点击安装")
                        .setProgress(0, 0, false)
                        .setDefaults(Notification.DEFAULT_ALL);
                Notification notification = _mBuilder.build();
                notification.flags = Notification.FLAG_AUTO_CANCEL;
                mNotificationManager.notify(NOTIFY_ID, notification);
            } else {
                Intent intent = installIntent(file.getAbsoluteFile());
                PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext()
                        , 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(pIntent)
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText("下载完成，点击安装")
                        .setProgress(0, 0, false)
                        .setDefaults(Notification.DEFAULT_ALL);
                Notification notification = mBuilder.build();
                notification.flags = Notification.FLAG_AUTO_CANCEL;
                mNotificationManager.notify(NOTIFY_ID, notification);
            }

        }
    }


    /**
     * 路径为根目录
     * 创建文件名称为 updateDemo.apk
     */
    private File createFile() {
        String root = Environment.getExternalStorageDirectory().getPath();
        File file = new File(root, "updateDemo.apk");
        if (file.exists())
            file.delete();
        try {
            file.createNewFile();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 把处理结果放回ui线程
     */
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    prepare();
                    break;

                case 1:
                    mNotificationManager.cancel(NOTIFY_ID);
                    fail((String) msg.obj);
                    break;

                case 2: {
                    int progress = (int) msg.obj;
                    progress(progress);
                    if (android.os.Build.VERSION.SDK_INT >= 26) {
                        _mBuilder.setContentTitle("正在下载：新版本...")
                                .setContentText(String.format(Locale.CHINESE, "%d%%", progress))
                                .setProgress(100, progress, false)
                                .setWhen(System.currentTimeMillis());
                        Notification notification = _mBuilder.build();
                        notification.flags = Notification.FLAG_AUTO_CANCEL;
                        mNotificationManager.notify(NOTIFY_ID, notification);
                    } else {
                        mBuilder.setContentTitle("正在下载：新版本...")
                                .setContentText(String.format(Locale.CHINESE, "%d%%", progress))
                                .setProgress(100, progress, false)
                                .setWhen(System.currentTimeMillis());
                        Notification notification = mBuilder.build();
                        notification.flags = Notification.FLAG_AUTO_CANCEL;
                        mNotificationManager.notify(NOTIFY_ID, notification);
                    }
                }
                break;

                case 3: {
                    mNotificationManager.cancelAll();
                    complete((File) msg.obj);
                }
                case 4: {
                    mNotificationManager.cancel(NOTIFY_ID);
                }
                break;
            }
            return false;
        }
    });


    /**
     * 是否运行在用户前面
     */
    private boolean onFront() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null || appProcesses.isEmpty())
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(getPackageName()) &&
                    appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }


    /**
     * 安装
     * 7.0 以上记得配置 fileProvider
     */
    private Intent installIntent(File file) {
        try {
            String authority = getApplicationContext().getPackageName() + ".provider";
            Uri fileUri = FileProvider.getUriForFile(getApplicationContext(), authority, file);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(fileUri, "application/vnd.android.package-archive");
            } else {
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            }
            return intent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 销毁时清空一下对notify对象的持有
     */
    @Override
    public void onDestroy() {
        mNotificationManager = null;
        super.onDestroy();
    }
}
