package com.example.dllo.foodpie.dbtool;

import android.os.Handler;
import android.os.Looper;

import com.example.dllo.foodpie.base.MyApp;
import com.example.dllo.foodpie.databean.CollectBean;
import com.litesuits.orm.LiteOrm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by dllo on 16/10/28.
 * 数据库的操作类
 */
public class CollectDBTool {
    //饿汉式 一加载就会申请  无法进行传值
    private static CollectDBTool sDBTool = new CollectDBTool();
    private LiteOrm liteOrm;
    private Handler handler;//用来做线程切换的
    private Executor mThreadPool;//线程池
    //构造方法私有化
    private CollectDBTool(){
        liteOrm = liteOrm.newSingleInstance(MyApp.getContext(), "foodPie.db");
        handler = new Handler(Looper.getMainLooper());//构造方法里这行代码可以保证这个handler一定是属于主线程的
        int coreNum = Runtime.getRuntime().availableProcessors();
        mThreadPool = Executors.newFixedThreadPool(coreNum + 1);
    }
    public static CollectDBTool getInstance(){
        return sDBTool;
    }


    public void insertCollectBean(final List<CollectBean> collectBean){
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.insert(collectBean);
            }
        });
    }

    //插入方法
    public void insertCollectBean(final CollectBean collectBean){
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.insert(collectBean);

            }
        });
    }

    //删除方法
    public void deleteCollectBean(final Class<CollectBean> collectBean){
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.delete(collectBean);
            }
        });
    }

    public void queryByIdCollectBean(final String name, final Class<CollectBean> collectBean){
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.queryById(name,collectBean);
            }
        });
    }

    //查询方法
    public void queryAllCollectBean(final OnQueryListener onQueryListener){
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
            ArrayList<CollectBean> query = liteOrm.query(CollectBean.class);
            handler.post(new CallBackRunnable(mOnQueryListener, query));
        }
    }
    //Handler使用的 将查询的数据返回到主线程使用的Runnable
    class CallBackRunnable implements Runnable{
        private OnQueryListener mOnQueryListener;
        private List<CollectBean> collectBean;

        public CallBackRunnable(OnQueryListener mOnQueryListener, ArrayList<CollectBean> collectBean) {
            this.mOnQueryListener = mOnQueryListener;
            this.collectBean = collectBean;
        }
        @Override
        public void run() {
            mOnQueryListener.onQuery(collectBean);
        }
    }
    //返回查询结果用的接口
    public  interface  OnQueryListener{
        void onQuery(List<CollectBean> collectBean);
    }

}
