package com.gzl0ng.sync;

/**
 * @author:郭正龙
 * @data:2023/4/13
 */

//第一步  创建资源类，定义属性和操作方法
class Share{
    //初始值
    private int number = 0;
    //+1的方法
    public synchronized void incr() throws InterruptedException {
        //第二步 判断 干活 通知
        while (number != 0){//判断number是否为0
            this.wait();//哪里睡，哪里醒
        }
        //如果number值是0，就+1操作
        number++;
        System.out.println(Thread.currentThread().getName() + " :: " + number);
        //通知其他线程
        this.notifyAll();
    }
    //-1的方法
    public synchronized void decr() throws InterruptedException {
//第二步 判断 干活 通知
        while (number != 1){//判断number是否为0
            this.wait();
        }
        //干活
        number--;
        System.out.println(Thread.currentThread().getName() + " :: " + number);
        //通知其他线程
        this.notifyAll();
    }
}
public class ThreadDemo1 {
    //第三步 创建多个线程，调用资源类的方法
    public static void main(String[] args) {
        Share share = new Share();
        //创建二个线程
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

        //虚假唤醒
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
