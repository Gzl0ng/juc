package com.gzl0ng.sync;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author:郭正龙
 * @data:2023/4/14
 *
 * List集合线程不安全
 */
public class ThreadDemo4 {
    public static void main(String[] args) {
        //创建ArrayList集合
//        ArrayList<Object> list = new ArrayList<>();

        /*
        解决方案:
         */
        //Vector
//        Vector<Object> list = new Vector<>();
        //Collections
//        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        //CopyOnWriteArrayList
//        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            new Thread(()->{
//             //向集合添加内容
//                list.add(UUID.randomUUID().toString().substring(0,8));
//             //从集合获取内容
//                System.out.println(list);
//            },String.valueOf(i)).start();
//        }



        //演示HashSet
//        HashSet<Object> set = new HashSet<>();

            /*
        解决方案:
         */
//        CopyOnWriteArraySet<Object> set = new CopyOnWriteArraySet<>();
//        for (int i = 0; i < 10; i++) {
//            new Thread(()->{
//                //向集合添加内容
//              set.add(UUID.randomUUID().toString().substring(0,8));
//                //从集合获取内容
//                 System.out.println(set);
//            }).start();
//        }

        //演示HashMap
//        HashMap<Object, Object> map = new HashMap<>();
         /*
        解决方案:
         */
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 10; i++) {
            String key = String.valueOf(i);
            new Thread(()->{
                //向集合添加内容
                map.put(key,UUID.randomUUID().toString().substring(0,8));
                //从集合获取内容
                System.out.println(map);
            }).start();
        }
    }
}
