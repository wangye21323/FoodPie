package com.example.dllo.foodpie.web;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by dllo on 16/10/24.
 */
public class MemoryCache implements ImageLoader.ImageCache{
    private LruCache<String, Bitmap> mCache;

    public MemoryCache() {
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
        mCache = new LruCache<String, Bitmap>(maxSize){
            protected int sizeOf(String key, Bitmap value){
                return value.getByteCount();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        //压缩一下图片的色彩
//        bitmap.setConfig(Bitmap.Config.RGB_565);
//       bitmap = bitmap.copy(Bitmap.Config.RGB_565,true);
        mCache.put(url, bitmap);
    }
}
