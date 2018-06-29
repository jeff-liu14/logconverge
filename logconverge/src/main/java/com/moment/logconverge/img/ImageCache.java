package com.moment.logconverge.img;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by moment on 2018/1/30.
 */

public class ImageCache {

    private static Context mContext;
    private static String imageUrl;
    private static final ImageCache INSTANCE = new ImageCache();

    public static ImageCache with(Context context) {
        mContext = context;
        return INSTANCE;
    }

    public static ImageCache load(String url) {
        imageUrl = url;
        return INSTANCE;
    }

    public static void into(ImageView imageView) {
        if (imageUrl != null && !"".equals(imageUrl)) {

        }
    }

}
