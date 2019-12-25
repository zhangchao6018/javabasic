package com.example.javabasic.lock;

import java.util.concurrent.TimeUnit;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-20 17:13
 *
 * 如何排除死锁
 *
 * 1.linux  ps -ef|grep ****   ls -l
 *      -rw-r--r--  1 zhangchao  staff  1357 Oct 20 17:37 DeadLockDemo.java
 * 2.jstack 1357
 *
 * windows下java运行程序  类似命令   jps=java ps   clerajps -l
 *
 * 3.jstack + 进程号pid
 **/

class HoldlockThread implements Runnable
{

    private String lockA;
    private String lockB;

    public HoldlockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA)
        {
            out.println(currentThread().getName() + "\t 自己持有："+lockA +"\t 尝试获得"+lockB);
            try { TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
            synchronized (lockB)
            {
                out.println(currentThread().getName() + "\t 自己持有："+lockB +"\t 尝试获得"+lockA);

            }
        }

    }
}
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldlockThread(lockA, lockB),"threadAAA").start();
        new Thread(new HoldlockThread(lockB, lockA),"threadBBB").start();
    }
}
