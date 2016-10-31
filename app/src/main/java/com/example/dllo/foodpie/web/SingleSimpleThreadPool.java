package com.example.dllo.foodpie.web;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by dllo on 16/10/10.
 * 线程池的单例
 */
public class SingleSimpleThreadPool {
    private static SingleSimpleThreadPool simpleThreadPool;
    private static ThreadPoolExecutor poolExecutor;

    public ThreadPoolExecutor getPoolExecutor() {
        return poolExecutor;
    }

    private SingleSimpleThreadPool() {
        int cpuCore = Runtime.getRuntime().availableProcessors();
        poolExecutor = new ThreadPoolExecutor(cpuCore + 1, cpuCore * 2 + 1, 60l, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
    }
    public static SingleSimpleThreadPool getSimpleThreadPool(){
        if (simpleThreadPool == null){
            synchronized (SingleSimpleThreadPool.class){
                if (simpleThreadPool == null){
                    simpleThreadPool = new SingleSimpleThreadPool();
                }
            }
        }
        return simpleThreadPool;
    }
}
