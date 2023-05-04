package com.gzl0ng.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author:郭正龙
 * @data:2023/4/24
 *
 * 集齐七颗龙珠召唤神龙
 *
 * 与CountDownLatch类似，不过是往上加，都是最终才能执行收尾线程
 */
public class CyclicBarrierDemo {
    //创建固定值
    private static final int NUMBER = 7;
    public static void main(String[] args) {
        //创建CyclicBarrier
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER,()->{
            System.out.println("集齐七颗龙珠召唤神龙");
        });
        //集齐七颗龙珠
        for (int i = 1; i <= 7; i++) {
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName() + " 星龙珠被收集到了");

                    //等待
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            },String.valueOf(i)).start();
        }
    }
}
