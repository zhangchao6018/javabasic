package com.example.javabasic.producer_comsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;

/**
 * sysnchronized  wait notify->  Lock Condition.await signal
 *
 *
 * 多线程之间按照顺序，实现a-b-c 三个线程启动
 * a打印5次，b打印10次，c打印15次
 * a打印5次，b打印10次，c打印15次
 * 。。。
 * 反复来十次
 *
 *
 * 紧接着
 *
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-19 19:52
 **/

class ShareResource
{
    private int number = 1;

    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();


    public void print5()
    {
        //上面可以写不需要加锁的代码
        lock.lock();
        try
        {
            //1.判断
            while (number !=1 )
            {
                c1.await();
            }
            //2. 干活
            for (int i=1; i<=5; i++){
                out.println(currentThread().getName() + "\t "+i);

            }
            //3.通知
            number=2;
            c2.signal();
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
            //1.判断
            while (number !=2 )
            {
                c2.await();
            }
            //2. 干活
            for (int i=1; i<=10; i++){
                out.println(currentThread().getName() + "\t "+i);

            }
            //3.通知
            number=3;
            c3.signal();
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
            //1.判断
            while (number !=3 )
            {
                c3.await();
            }
            //2. 干活
            for (int i=1; i<=15; i++){
                out.println(currentThread().getName() + "\t "+i);

            }
            //3.通知
            number=1;
            c1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

         new Thread(() -> {
             for (int i=1; i<=10; i++){
                 shareResource.print5();
             }
         },"AA" ).start();

        new Thread(() -> {
            for (int i=1; i<=10; i++){
                shareResource.print10();
            }
        },"BB" ).start();

        new Thread(() -> {
            for (int i=1; i<=10; i++){
                shareResource.print15();
            }
        },"CC" ).start();

    }
}
