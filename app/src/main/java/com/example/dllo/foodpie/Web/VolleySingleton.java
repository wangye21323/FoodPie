package com.example.dllo.foodpie.Web;

import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.dllo.foodpie.R;

/**
 * Created by dllo on 16/10/24.
 */
public class VolleySingleton {
    private static  VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private ImageLoader mImageLoader;
    private VolleySingleton(){
        requestQueue = Volley.newRequestQueue(MyApp.getContext());//自己声明的上下文, myApp
        mImageLoader = new ImageLoader(requestQueue, new MemoryCache());//初始化ImageLoader
    }
    public static VolleySingleton getInstance(){
        if (volleySingleton == null){
            synchronized (VolleySingleton.class){
                if (volleySingleton == null){
                    volleySingleton = new VolleySingleton();
                }
            }
        }
        return volleySingleton;
    }

    public void getImage(String url, ImageView imageView){
        mImageLoader.get(url, ImageLoader.getImageListener
                (imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher));
    }
    public RequestQueue getRequestQueue(){
        return requestQueue;
    }
    public <T> void addRequest(Request<T> request){
        requestQueue.add(request);
    }
}
