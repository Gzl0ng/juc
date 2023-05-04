package com.gzl0ng.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:郭正龙
 * @data:2023/4/13
 */

//第一步  创建资源类，定义属性和操作方法
class Share{
    //初始值
    private int number = 0;
    //创建Lock
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    //+1的方法
    public void incr() throws InterruptedException {


        try {
            //上锁
            lock.lock();
            //判断
            while (number !=0){
                condition.await();
            }

            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + " :: " + number);
            //通知
            condition.signalAll();
        }finally {
            //解锁
            lock.unlock();
        }
    }
    //-1的方法
    public void decr() throws InterruptedException {
        try {
            //上锁
            lock.lock();
            //判断
            while (number != 1) {
                condition.await();
            }

            //干活
            number--;
            System.out.println(Thread.currentThread().getName() + " :: " + number);
            //通知
            condition.signalAll();
        } finally {
            //解锁
            lock.unlock();
        }
    }
}
public class ThreadDemo2 {

    public static void main(String[] args) {
        Share share = new Share();

        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"CC").start();
        new Thread(()->{
            for (int i = 0; i < 30; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"DD").start();
    }
}
