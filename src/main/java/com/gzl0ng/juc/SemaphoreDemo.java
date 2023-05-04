package com.gzl0ng.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author:郭正龙
 * @data:2023/4/24
 *
 * 6辆汽车，停三个车位
 *
 * 线程抢占问题，多个线程，只有有限个线程能执行。
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        //创建Semaphore ,设置许可数量
        Semaphore semaphore = new Semaphore(3);

        //模拟6辆汽车
        for (int i = 1; i <=6; i++) {
            new Thread(() -> {
                try {
                    //抢占
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "启抢到了车位");

                    //设置随机停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));

                    System.out.println(Thread.currentThread().getName() + "----离开了车位");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    //释放
                    semaphore.release();
                }

            },String.valueOf(i)).start();
        }
    }
}
