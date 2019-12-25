package com.example.javabasic.producer_comsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;

/**
 * @Description:
 * 线程通信之生产者消费者传统版
 * 多线程环境 共享资源用while判断
 * @Author: zhangchao
 * @Date: 2019-10-19 11:41
 **/

class ShareData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increase() throws InterruptedException {
        lock.lock();
        try
        {
            //1.判断
            while (number!=0){
                condition.await();
            }
            //2.干活
            number++;
            out.println(currentThread().getName() + "\t "+number);

            //3.唤醒
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public void decrease() throws InterruptedException {
        lock.lock();
        try
        {
            //1.判断
            while (number==0){
                condition.await();
            }
            //2.干活
            number-- ;
            out.println(currentThread().getName() + "\t "+number);
            //3.唤醒
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class Prod_ComsumerTraditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i=1; i<=5; i++){
                try {
                    shareData.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        },"AA" ).start();

        new Thread(() -> {
            for (int i=1; i<=5; i++){
                try {
                    shareData.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        },"BB" ).start();
    }

}
