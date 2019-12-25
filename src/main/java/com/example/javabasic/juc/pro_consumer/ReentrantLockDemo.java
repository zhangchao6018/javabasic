package com.example.javabasic.juc.pro_consumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-11-03 19:56
 * 多线程之间按照顺序，实现a-b-c 三个线程启动
 *  * a打印5次，b打印10次，c打印15次
 *  * a打印5次，b打印10次，c打印15次
 **/
class commonData
{
    private int resource =0;

    private Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void print5()
    {
        lock.lock();
        try
        {
            while (resource!=0)
            {
                condition1.await();
            }
            for (int i=1; i<=5; i++){
                out.println(currentThread().getName() + "\t 执行print");
            }
            resource++;
            condition2.signal();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void print10()
    {
        lock.lock();
        try
        {
            while (resource!=1)
            {
                condition2.await();
            }
            for (int i=1; i<=10; i++){
                out.println(currentThread().getName() + "\t 执行print");
            }
            resource++;
            condition3.signal();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void print15()
    {
        lock.lock();
        try
        {
            while (resource!=2)
            {
                condition3.await();
            }
            for (int i=1; i<=15; i++){
                out.println(currentThread().getName() + "\t 执行print");
            }
            resource=0;
            condition1.signal();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class ReentrantLockDemo {
    public static void main(String[] args) {
        commonData commonData = new commonData();

        new Thread(() -> {
           for (int i=1; i<=10; i++){
               commonData.print5();
           }
        },"AA" ).start();

        new Thread(() -> {
            for (int i=1; i<=10; i++){
                commonData.print10();
            }
        },"BB" ).start();

        new Thread(() -> {
            for (int i=1; i<=10; i++){
                commonData.print15();
            }
        },"CC" ).start();
    }
}
