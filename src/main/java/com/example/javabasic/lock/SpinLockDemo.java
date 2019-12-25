package com.example.javabasic.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 自旋锁
 * 不用阻塞
 *
 * 坏处 长时间循环  系统性能下降
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-17 08:44
 **/
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();


    public void myLock(){
        Thread thread = Thread.currentThread() ;
        System.out.println(Thread.currentThread().getName() + "\t come in myLock");

        while (!atomicReference.compareAndSet(null, thread)){

        }
    }

    public void myUnLock(){
        Thread thread = Thread.currentThread();

        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t invoked  myUnlock");

    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.myLock();
            try { SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
            spinLockDemo.myUnLock();

        },"thread1" ).start();

        try { TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
        new Thread(() -> {
            spinLockDemo.myLock();
            try { SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}



            spinLockDemo.myUnLock();
        },"thread2 " ).start();
    }
}
