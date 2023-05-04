package com.gzl0ng.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author:郭正龙
 * @data:2023/4/25
 *
 * 演示线程池三种常用分类
 */
public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        //一池五线程
        ExecutorService executorService = Executors.newFixedThreadPool(5);//5个窗口

        //一池一线程
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();//一个窗口

        //一池可扩容线程
        ExecutorService executorService2 = Executors.newCachedThreadPool();

        //10个顾客请求
        try {
            for (int i = 0; i < 20; i++) {
                executorService2.execute(()->{
                    System.out.println(Thread.currentThread().getName() + " 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭
            executorService2.shutdown();
        }
    }
}
