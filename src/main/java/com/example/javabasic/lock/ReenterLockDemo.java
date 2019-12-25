package com.example.javabasic.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 可重入锁（递归锁）
 *
 * eg：
 * synchronized/ReentrantLock
 *
 * @Author: zhangchao
 * @Date: 2019-10-16 23:26
 **/
class phone implements Runnable
{
    public  synchronized void sendMsg()
    {
        System.out.println(Thread.currentThread().getName()+"\t invoked sendMsg" );
        sendEmail();
    }

    public  synchronized void sendEmail()
    {
        System.out.println(Thread.currentThread().getName()+"\t invoked sendEmail" );
    }

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoked get");
            set();
        }finally {
            lock.unlock();
        }

    }
    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoked set");
        }finally {
            lock.unlock();
        }
    }

}

public class ReenterLockDemo {

    public static void main(String[] args) {
        phone phone = new phone();
        new Thread(() -> {
            phone.sendMsg();
        },"thread1" ).start();


        new Thread(() -> {
            phone.sendMsg();
        },"thread2" ).start();


        Thread thread3 = new Thread(phone,"thread3");

        Thread thread4 = new Thread(phone, "thread4");

        thread3.start();
        thread4.start();

    }
}
