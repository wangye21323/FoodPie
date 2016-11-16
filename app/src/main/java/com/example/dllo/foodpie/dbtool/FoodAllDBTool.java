package com.example.dllo.foodpie.dbtool;

import android.os.Handler;
import android.os.Looper;

import com.example.dllo.foodpie.base.MyApp;
import com.example.dllo.foodpie.databean.FoodAllDbBean;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by dllo on 16/10/28.
 * 数据库的操作类
 */
public class FoodAllDBTool {
    //饿汉式 一加载就会申请  无法进行传值
    private static FoodAllDBTool sDBTool = new FoodAllDBTool();
    private LiteOrm liteOrm;
    private Handler handler;//用来做线程切换的
    private Executor mThreadPool;//线程池

    //构造方法私有化
    private FoodAllDBTool() {
        liteOrm = liteOrm.newSingleInstance(MyApp.getContext(), "foodPie.db");
        handler = new Handler(Looper.getMainLooper());//构造方法里这行代码可以保证这个handler一定是属于主线程的
        int coreNum = Runtime.getRuntime().availableProcessors();
        mThreadPool = Executors.newFixedThreadPool(coreNum + 1);
    }

    public static FoodAllDBTool getInstance() {
        return sDBTool;
    }


    public void insertFoodAllDbBean(final List<FoodAllDbBean> foodAllDbBean) {
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.insert(foodAllDbBean);
            }
        });
    }

    //插入方法
    public void insertFoodAllDbBean(final FoodAllDbBean foodAllDbBean) {
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.insert(foodAllDbBean);

            }
        });
    }

    //删除表方法
    public void deleteFoodAllDbBean(final Class<FoodAllDbBean> foodAllDbBean) {
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.delete(foodAllDbBean);
            }
        });
    }

    //删除某一数值
    public void deleteValueBean(final Class<FoodAllDbBean> foodAllDbBean, final String field, final String[] value) {
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                liteOrm.delete(foodAllDbBean, WhereBuilder.create(foodAllDbBean).where(field + "=?", value));
            }
        });
    }

    //这个方式的查询或者是插入, 没有返回值的, 都是通过接口的形式
    public void queryByValuesFoodAllDbBean(final Class<FoodAllDbBean> foodAllDbBean,
                                         final String field, final String[] value,
                                         final OnQueryListener onQueryListener) {
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                List<FoodAllDbBean> data = liteOrm.query(new QueryBuilder(foodAllDbBean).where(field + "= ?", value));
                onQueryListener.onQuery(data);
            }
        });
    }

    //查询方法
    public void queryAllFoodAllDbBean(final OnQueryListener onQueryListener) {
        //查询
        mThreadPool.execute(new QueryRunnable(onQueryListener));
    }

    class QueryRunnable implements Runnable {//查询
        private OnQueryListener mOnQueryListener;

        public QueryRunnable(OnQueryListener mOnQueryListener) {
            this.mOnQueryListener = mOnQueryListener;
        }

        @Override
        public void run() {
            ArrayList<FoodAllDbBean> query = liteOrm.query(FoodAllDbBean.class);
            handler.post(new CallBackRunnable(mOnQueryListener, query));
        }
    }

    //Handler使用的 将查询的数据返回到主线程使用的Runnable
    class CallBackRunnable implements Runnable {
        private OnQueryListener mOnQueryListener;
        private List<FoodAllDbBean> foodAllDbBean;

        public CallBackRunnable(OnQueryListener mOnQueryListener, ArrayList<FoodAllDbBean> foodAllDbBean) {
            this.mOnQueryListener = mOnQueryListener;
            this.foodAllDbBean = foodAllDbBean;
        }

        @Override
        public void run() {
            mOnQueryListener.onQuery(foodAllDbBean);
        }
    }

    //返回查询结果用的接口
    public interface OnQueryListener {
        void onQuery(List<FoodAllDbBean> foodAllDbBean);
    }

}
