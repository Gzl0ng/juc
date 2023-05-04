package com.gzl0ng.callable.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author:郭正龙
 * @data:2023/5/1
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //异步调用，没有返回值
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "completableFuture1");
        });
        completableFuture1.get();

        //异步调用 有返回值
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + "completableFuture2");
            //模拟异常
            int i = 1/0;
            return i;
        });
        Integer integer = completableFuture2.whenComplete((t,u)->{
            System.out.println("---t=" + t);
            System.out.println("---u=" + u);
        }).get();

    }
}
