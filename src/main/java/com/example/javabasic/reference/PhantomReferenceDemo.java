package com.example.javabasic.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description:gc后放置在引用队列，类似后置通知
 * @Author: zhangchao
 * @Date: 2019-10-22 00:21
 **/
public class PhantomReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        ReferenceQueue<Object> queue = new ReferenceQueue();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1,queue);
        System.out.println("o1:"+o1);
        System.out.println(phantomReference.get());
        System.out.println(queue.poll());

        System.out.println("---------------------");
        o1=null;
        System.gc();
        try { TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("o1:"+o1);
        System.out.println(phantomReference.get());
        System.out.println(queue.poll());
    }
}
