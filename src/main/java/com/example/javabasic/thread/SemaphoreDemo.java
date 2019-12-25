package com.example.javabasic.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static java.lang.String.valueOf;

/**信号量
 *信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制(限流)。
 *
 * 如果把permits=1，可以实现类似synchronized功能
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-18 08:12
 **/
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

         for (int i=1; i<=1; i++)
          {
              new  Thread(() -> {
                  try {
                      semaphore.acquire();
                      System.out.println(Thread.currentThread().getName() + "\t 抢到了车位");
                      try { TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
                      System.out.println(Thread.currentThread().getName() + "\t 停车3秒离开了车位");
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }finally {
                      semaphore.release();
                  }
             }, valueOf(i)).start();
         }
    }
}
