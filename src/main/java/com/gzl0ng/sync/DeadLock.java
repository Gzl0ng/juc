package com.gzl0ng.sync;

import java.util.concurrent.TimeUnit;

/**
 * @author:郭正龙
 * @data:2023/4/14
 *
 * 死锁
 */
public class DeadLock {
    //创建二个对象
    static Object a = new Object();
    static Object b = new Object();


    public static void main(String[] args) {
        new Thread(()->{
            synchronized (a){
                System.out.println(Thread.currentThread().getName()+"持有锁a，试图获取锁b");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (b){
                    System.out.println(Thread.currentThread().getName()+"获取锁b");
                }
            }
        },"A").start();

        new Thread(()->{
            synchronized (b){
                System.out.println(Thread.currentThread().getName()+"持有锁a，试图获取锁b");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (a){
                    System.out.println(Thread.currentThread().getName()+"获取锁b");
                }
            }
        },"B").start();
    }
}
