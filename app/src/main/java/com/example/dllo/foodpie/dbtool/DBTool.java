package com.example.dllo.foodpie.dbtool;

import android.os.Handler;
import android.os.Looper;

import com.example.dllo.foodpie.base.MyApp;
import com.example.dllo.foodpie.databean.SearchData;
import com.litesuits.orm.LiteOrm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by dllo on 16/10/28.
 * 数据库的操作类
 */
public class DBTool {
    //饿汉式 一加载就会申请  无法进行传值
    private static DBTool sDBTool = new DBTool();
    private LiteOrm liteOrm;
    private Handler handler;//用来做线程切换的
    private Executor mThreadPool;//线程池
    //构造方法私有化
    private DBTool(){
        liteOrm = liteOrm.newSingleInstance(MyApp.getContext(), "foodPie.db");
        handler = new Handler(Looper.getMainLooper());//构造方法里这行代码可以保证这个handler一定是属于主线程的
        int coreNum = Runtime.getRuntime().availableProcessors();
        mThreadPool = Executors.newFixedThreadPool(coreNum + 1);
    }
    public static DBTool getInstance(){
        return sDBTool;
    }


    public void insertSearchData(final List<SearchData> searchDatas){
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.insert(searchDatas);
            }
        });
    }

    //插入方法
    public void insertSearchData(final SearchData searchData){
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.insert(searchData);

            }
        });
    }

    //删除方法
    public void deleteSearchData(final Class<SearchData> searchData){
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.delete(searchData);
            }
        });
    }

    //查询方法
    public void queryAllSearchData(final OnQueryListener onQueryListener){
        //查询
        mThreadPool.execute(new QueryRunnable(onQueryListener));
    }

    class QueryRunnable implements Runnable{//查询
        private OnQueryListener mOnQueryListener;

        public QueryRunnable(OnQueryListener mOnQueryListener) {
            this.mOnQueryListener = mOnQueryListener;
        }

        @Override
        public void run() {
            ArrayList<SearchData> query = liteOrm.query(SearchData.class);
            handler.post(new CallBackRunnable(mOnQueryListener, query));
        }
    }
    //Handler使用的 将查询的数据返回到主线程使用的Runnable
    class CallBackRunnable implements Runnable{
        private OnQueryListener mOnQueryListener;
        private List<SearchData> searchDatas;

        public CallBackRunnable(OnQueryListener mOnQueryListener, List<SearchData> searchDatas) {
            this.mOnQueryListener = mOnQueryListener;
            this.searchDatas = searchDatas;
        }
        @Override
        public void run() {
            mOnQueryListener.onQuery(searchDatas);
        }
    }
    //返回查询结果用的接口
    public  interface  OnQueryListener{
        void onQuery(List<SearchData> person);
    }

}
