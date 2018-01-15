package com.moment.logconverge.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.moment.logconverge.cache.util.ACache;

import java.io.ByteArrayOutputStream;

/**
 * Created by moment on 2017/12/12.
 */

public class Utils {

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        int i;
        int j;
        if (bmp.getHeight() > bmp.getWidth()) {
            i = bmp.getWidth();
            j = bmp.getWidth();
        } else {
            i = bmp.getHeight();
            j = bmp.getHeight();
        }

        Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.RGB_565);
        Canvas localCanvas = new Canvas(localBitmap);

        while (true) {
            localCanvas.drawBitmap(bmp, new Rect(0, 0, i, j), new Rect(0, 0, i, j), null);
            if (needRecycle)
                bmp.recycle();
            ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
            localBitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                    localByteArrayOutputStream);
            localBitmap.recycle();
            byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
            try {
                localByteArrayOutputStream.close();
                return arrayOfByte;
            } catch (Exception e) {
                //F.out(e);
            }
            i = bmp.getHeight();
            j = bmp.getHeight();
        }
    }

    public static String getTimeExpend(long startTime, long endTime) {
        //传入字串类型 2016/06/28 08:30
        long longStart = startTime; //获取开始时间毫秒数
        long longEnd = endTime;  //获取结束时间毫秒数
        long longExpend = longEnd - longStart;  //获取时间差

        long longHours = longExpend / (60 * 60 * 1000); //根据时间差来计算小时数
        long longMinutes = (longExpend - longHours * (60 * 60 * 1000)) / (60 * 1000);   //根据时间差来计算分钟数
        long longSec = (longExpend - longHours * (60 * 60 * 1000) - longMinutes * (60 * 1000)) / 1000;

        return longHours + "h" + longMinutes + "m" + longSec + "s";
    }


    private static ACache mACache;

    public static ACache getmACache(Context context) {
        synchronized (Utils.class) {
            if (mACache == null) {
                mACache = ACache.get(context);
            }
            return mACache;
        }
    }
}
