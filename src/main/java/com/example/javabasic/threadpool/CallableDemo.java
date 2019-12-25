package com.example.javabasic.threadpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-19 22:43
 *
 * 获取多线程的方式
 *
 **/
class MyThread1 implements Runnable
{

    @Override
    public void run() {

    }
}

/**
 * 带返回值的多线程编程方式
 */
class MyThread implements Callable<Integer>
{

    /**
     * 为什么返回值 并发情况多次调用有成功有失败
     * @return
     * @throws Exception
     */
    @Override
    public Integer call() throws Exception {
        System.out.println("========== come in Callable");
        try { TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}

        return 1024;
    }
}

/**
 * 注意：多个线程持有futureTask 只会调用一次call（）
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //FutureTask(Callable<V> callable)
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask,"AA").start();
        //这种方式只会调用一次call()方法，除非new两个不同FutureTask()
        new Thread(futureTask,"BB").start();
        //Integer integer = futureTask.get();
        System.out.println("主线程其他业务代码***************");
        int result = 100;


        while (!futureTask.isDone())
        {
            //等待
        }

        //get()方法是阻塞的，需要等到call()方法执行完毕，推荐放最后取获取
        Integer integer = futureTask.get();
        System.out.println("final result："+(result+integer));
    }
}
