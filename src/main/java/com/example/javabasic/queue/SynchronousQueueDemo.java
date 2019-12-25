package com.example.javabasic.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-19 10:18
 **/
public class SynchronousQueueDemo {
    public static void main(String[] args)  {
        //同步队列  产生一个消费一个
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                out.println(currentThread().getName() + "\t put 1");
                blockingQueue.put("1");

                out.println(currentThread().getName() + "\t put 2");
                blockingQueue.put("2");

                out.println(currentThread().getName() + "\t put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA" ).start();

        new Thread(() -> {
            try
            {
                TimeUnit.SECONDS.sleep(5);
                out.println(currentThread().getName() + "\t take"+ blockingQueue.take());

                TimeUnit.SECONDS.sleep(5);
                out.println(currentThread().getName() + "\t take"+ blockingQueue.take());

                TimeUnit.SECONDS.sleep(5);
                out.println(currentThread().getName() + "\t take"+ blockingQueue.take());
            } catch (InterruptedException e) {e.printStackTrace();}


        },"BB" ).start();
    }

}
