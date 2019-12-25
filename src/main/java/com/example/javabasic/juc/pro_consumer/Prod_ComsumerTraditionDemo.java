package com.example.javabasic.juc.pro_consumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;

/**
 * @Description:
 * 线程通信之生产者消费者传统版（需求：1-0，1-0，1-0 交替）
 * 多线程环境 共享资源用while判断
 * 如果用if 只判断一次 ，会出现虚假唤醒，导致数据逻辑错误
 *
 *
 * 1.高内聚低耦合前提下，线程操作资源类
 * 2.判断、干活、通知
 * 3.多线程交互中，必须防止多线程的虚假唤醒，也即只用while，不能用if
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

        },"BB" ).start(); new Thread(() -> {
            for (int i=1; i<=5; i++){
                try {
                    shareData.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        },"CC" ).start();

        new Thread(() -> {
            for (int i=1; i<=5; i++){
                try {
                    shareData.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        },"DD" ).start();

    }

}
