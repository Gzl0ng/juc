package com.gzl0ng.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author:郭正龙
 * @data:2023/4/25
 *
 * 读锁无法升级为写锁
 */
public class Demo1 {
    public static void main(String[] args) {
        //可重入读写锁对象
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();//读锁
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();//写锁


        //锁降级
        //2.获取读锁
        readLock.lock();
        System.out.println("--read");

        //1.获取写锁
        writeLock.lock();
        System.out.println("gzl");


        //4.释放读锁
        readLock.unlock();

        //3.释放写锁
        writeLock.unlock();
    }
}
