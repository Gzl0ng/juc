package com.gzl0ng.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author:郭正龙
 * @data:2023/4/24
 *
 */
//资源类
class MyCache{
     //创建Map集合(volatile表示数据会多次读写修改)
    private volatile Map<String,Object> map = new HashMap<>();

    //读写锁对象
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();


    //放数据
    public void put(String key,Object value){
        //添加写锁
        rwLock.writeLock().lock();

        //模拟写过程
        try {
            System.out.println(Thread.currentThread().getName() + " 正在写操作" + key);

            TimeUnit.MICROSECONDS.sleep(300);
            //放入数据
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + " 写完了" + key);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            //释放写锁
            rwLock.writeLock().unlock();
        }
}

    //取数据
    public Object get(String key){
        Object result = null;

        //添加读锁
        rwLock.readLock().lock();

        //模拟取数据过程
        try {
            System.out.println(Thread.currentThread().getName() + " 正在读取操作" + key);

            TimeUnit.MICROSECONDS.sleep(300);
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + " 取完了" + key);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            //释放读锁
            rwLock.readLock().unlock();
        }


    }
}
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        //创建线程写数据
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(()->{
                myCache.put(num + "",num);
            },String.valueOf(i)).start();
        }

        //创建线程读数据
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread(()->{
                myCache.get(num + "");
            },String.valueOf(i)).start();
        }



    }
}
