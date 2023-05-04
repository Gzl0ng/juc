package com.gzl0ng.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author:郭正龙
 * @data:2023/4/15
 */

//实现Runnable接口
class MyThread  implements Runnable{


    @Override
    public void run() {

    }
}
//实现Callable接口
class MyThread2 implements Callable{

    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " come in callable");
        return 200;
    }
}
public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Runnable接口创建线程
        new Thread(new MyThread(),"AA").start();

        //Callable接口,报错
        FutureTask<Integer> futureTask1 = new FutureTask<Integer>(new MyThread2());
        //lambda表达式
        FutureTask<Integer> futureTask2 = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + " come in callable");
            return 1024;
        });

        //创建一个线程
        new Thread(futureTask2,"lucy").start();
        new Thread(futureTask1,"mary").start();

//        while (!futureTask2.isDone()){
//            System.out.println("wait...");
//        }
        //调用FutureTask的get方法
        System.out.println(futureTask2.get());

        //线程执行过一次后直接返回结果
        System.out.println(futureTask1.get());

        System.out.println(Thread.currentThread().getName() + " come over");
        //FutureTask原理 未来任务
        /**
         * 1.主线程正常执行，另开线程干别的事
         *
         * 2.4个线程干不同的事，先干完的先汇总可等待慢线程汇总结果。
         *
         * 3.先做简单的
         *
         * 汇总一次
         */
    }
}
