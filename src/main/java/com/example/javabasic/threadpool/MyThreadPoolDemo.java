package com.example.javabasic.threadpool;

import java.util.concurrent.*;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-20 10:21
 *
 * 第四种获得一级使用java多线程的方式：线程池 继承thread/实现runnable/callable/线程池
 **/
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //jdkBasicThreadPool();
        /**
         *     1.当饱和拒绝策略是AbortPolicy
         *          线程大于8（阈值）个可能出现异常：java.util.concurrent.RejectedExecutionException
         *     2.当饱和拒绝策略是AbortPolicy
         *          main    办理业务： 回退给调用者
         *     3.DiscardOldestPolicy
         *          抛弃队列等待最久的任务，然后把当前任务加入队列中尝试再次提交
         *     4.DiscardPolicy
         *          直接丢弃任务，不予任何处理，也不抛异常
         *
         */
        ExecutorService threadpool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        try {
            for (int i=1; i<=15; i++){
                threadpool.execute(() -> {
                    out.println(currentThread().getName() + "\t 办理业务");
                });
                //try { TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadpool.shutdown();
        }
    }

    /**
     * jdk 基础自带连接池（生产中需要手写）
     */
    static void jdkBasicThreadPool() {
        //1.1一池5个处理线程
        //ExecutorService threadpool = Executors.newFixedThreadPool(5);
        //1.2一池1线程
        //ExecutorService threadpool = Executors.newSingleThreadExecutor();
        //1.2一池多个线程
        ExecutorService threadpool = Executors.newCachedThreadPool();

        //2.关闭比使用更重要

        //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程

        try {
            for (int i=1; i<=20; i++){
                threadpool.execute(() -> {
                    out.println(currentThread().getName() + "\t 办理业务");
                });
                //try { TimeUnit.MILLISECONDS.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}

            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadpool.shutdown();
        }
    }
}
