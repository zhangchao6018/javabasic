package com.example.javabasic.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-22 00:12
 *
 * gc清除了的对象后 会在引用队列中存储相关信息
 **/
public class RefrenceQueueDemo
{
    public static void main(String[] args) {
        Object obj = new Object();
        ReferenceQueue<Object> refrenceQueue = new ReferenceQueue();
        WeakReference<Object> weakReference = new WeakReference<>(obj,refrenceQueue);
        System.out.println(obj);
        System.out.println(weakReference.get());
        System.out.println(refrenceQueue.poll());

        System.out.println("=====================");
        obj=null;
        System.gc();
        try { TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        System.out.println(obj);
        System.out.println(weakReference.get());
        System.out.println(refrenceQueue.poll());

    }
}
