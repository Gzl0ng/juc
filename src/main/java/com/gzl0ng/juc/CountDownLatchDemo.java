package com.gzl0ng.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author:郭正龙
 * @data:2023/4/24
 *
 * 班长线程需要最后释放，所以需要CountDownLatch的信号量标志
 */
public class CountDownLatchDemo {


    public static void main(String[] args) throws InterruptedException {

        //创建CountDownLatch对象，设置初始值
        CountDownLatch countDownLatch = new CountDownLatch(6);
        //6个同学陆续退出，班长线程才退出
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() +" 号同学退出");

                //计数 -1
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        //等待
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " 班长退出");
    }
}
