package com.moment.logconverge.cache.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.moment.logconverge.util.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by moment on 2018/1/15.
 */

public class LogToFile {
    private static String TAG = "LogToFile";

    private static String logPath = null;//log日志存放路径

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);//日期格式;

    private static Date date = new Date();//因为log日志是使用日期命名的，使用静态成员变量主要是为了在整个程序运行期间只存在一个.log文件中;

    /**
     * 初始化，须在使用之前设置，最好在Application创建时调用
     *
     * @param context
     */
    public static void init(Context context) {
        logPath = getFilePath(context) + "/Logs";//获得文件储存路径,在后面加"/Logs"建立子文件夹
    }

    /**
     * 获得文件存储路径
     *
     * @return
     */
    private static String getFilePath(Context context) {
        try {
            if (Environment.MEDIA_MOUNTED.equals(Environment.MEDIA_MOUNTED) || !Environment.isExternalStorageRemovable()) {//如果外部储存可用
                return context.getExternalFilesDir(null).getPath();//获得外部存储路径,默认路径为 /storage/emulated/0/Android/data/com.waka.workspace.logtofile/files/Logs/log_2016-03-14_16-15-09.log
            } else {
                return context.getFilesDir().getPath();//直接存在/data/data里，非root手机是看不到的
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static boolean log(String des, String msg) {
        try {
            return writeToFile(des, msg);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<String> getLogFileStr() {
        try {
            List<String> strings = new ArrayList<>();
            List<File> files = getFileDir(logPath);
            if (files != null) {
                for (int i = 0; i < files.size(); i++) {
                    String content = ""; //文件内容字符串
                    File file = files.get(i);
                    if (!file.isDirectory()) {
                        try {
                            InputStream instream = new FileInputStream(file);
                            if (instream != null) {
                                InputStreamReader inputreader = new InputStreamReader(instream);
                                BufferedReader buffreader = new BufferedReader(inputreader);
                                String line;
                                //分行读取
                                while ((line = buffreader.readLine()) != null) {
                                    content += line + "\n";
                                }
                                instream.close();
                            }
                        } catch (java.io.FileNotFoundException e) {
                            Log.d("TestFile", "The File doesn't not exist.");
                        } catch (IOException e) {
                            Log.d("TestFile", e.getMessage());
                        }

                    }
                    if (!StringUtils.isEmpty(content)) {
                        strings.add(content);
                    }
                }
            }
            return strings;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<File> getFileDir(String filePath) {
        List<File> fileList = new ArrayList<>();
        try {
            File file = new File(filePath);
            File[] files = file.listFiles();
            if (null != files) {
                for (int i = 0; i < files.length; i++) {
                    fileList.add(files[i]);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return fileList;
    }

    public static List<String> getAllFileName() {
        try {
            List<String> strings = new ArrayList<>();
            List<File> files = getFileDir(logPath);
            if (files != null) {
                for (int i = 0; i < files.size(); i++) {
                    String name = ""; //文件内容字符串
                    File file = files.get(i);
                    if (!file.isDirectory()) {
                        name = file.getName();
                    }
                    if (!StringUtils.isEmpty(name)) {
                        strings.add(name);
                    }
                }
            }
            return strings;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void deleteAllLogs() {
        try {
            File file = new File(logPath);
            recursionDeleteFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteFileByKey(String key) {
        String fileName = logPath + "/log_" + key + ".log";//log日志名，使用时间命名，保证不重复
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    private static void recursionDeleteFile(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                file.delete();
                return;
            }
            for (File f : childFile) {
                recursionDeleteFile(f);
            }
            file.delete();
        }
    }


    /**
     * 将log信息写入文件中
     *
     * @param key
     * @param msg
     */
    private static boolean writeToFile(String key, String msg) {

        if (null == logPath) {
            Log.e(TAG, "logPath == null ，未初始化LogToFile");
            return false;
        }

        String fileName = logPath + "/log_" + key + ".log";//log日志名，使用时间命名，保证不重复
        String log = dateFormat.format(date) + msg;//log日志内容，可以自行定制

        //如果父路径不存在
        File file = new File(logPath);
        if (!file.exists()) {
            file.mkdirs();//创建父路径
        }

        FileOutputStream fos = null;//FileOutputStream会自动调用底层的close()方法，不用关闭
        BufferedWriter bw = null;
        try {

            fos = new FileOutputStream(fileName, true);//这里的第二个参数代表追加还是覆盖，true为追加，flase为覆盖
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(log);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (bw != null) {
                    bw.close();//关闭缓冲流
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

    }
}
