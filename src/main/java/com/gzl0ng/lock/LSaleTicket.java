package com.gzl0ng.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:郭正龙
 * @data:2023/4/11
 */

//第一步  创建资源类，定义属性和操作方法
class LTicket{
    //票数
    private int number = 30;
    //创建可重入锁
    private final ReentrantLock lock = new ReentrantLock(true);
    //操作方法：卖票
    public synchronized void sale(){
        //上锁
        try {
            lock.lock();
            //卖票过程
            if (number>0){
                System.out.println(Thread.currentThread().getName() + " : 卖出: " + (number--) + " 剩下:" + number);
            }
        }finally {
            //解锁
            lock.unlock();
        }
    }
}
public class LSaleTicket {
    //第二步  创建多个线程,调用资源类的操作方法
    public static void main(String[] args) {
        //创建Ticket对象
        LTicket ticket = new LTicket();
        //创建三个线程
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"CC").start();
    }
}
