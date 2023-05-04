package com.gzl0ng.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author:郭正龙
 * @data:2023/5/1
 */
class MyTask extends RecursiveTask<Integer>{
    //拆分差值不能超过10，计算10以内运算
    private static final Integer value = 10;

    private int begin;//拆分开始值
    private int end;//拆分结束值
    private int result;//返回结果

    //创建有参构造
    public MyTask(int begin,int end){
        this.begin=begin;
        this.end=end;
    }

    //拆分和合并过程
    @Override
    protected Integer compute() {
        //判断相加二个数是否大于10
        if ((end-begin)<=10){
            //相加操作
            for (int i = begin; i < end; i++) {
                result = result+i;
                System.out.println(Thread.currentThread().getName());
            }
        }else {//进一步拆分
            //获取数据中间值
            int middle = (begin+end)/2;
            //拆分左边
            MyTask myTask01 = new MyTask(begin, middle);
            //拆分右边
            MyTask myTask02 = new MyTask(middle+1, end);

            //调用方法拆分
            myTask01.fork();
            myTask02.fork();
            //合并结果
            result = myTask01.join()+myTask02.join();
        }
        return result;
    }
}

public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建MyTask对象
        MyTask myTask = new MyTask(0, 100);
        //创建分支合并池对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);
        //获取最终合并之后结果
        Integer result = forkJoinTask.get();
        System.out.println(result);

        //关闭池对象
        forkJoinPool.shutdown();
    }
}
