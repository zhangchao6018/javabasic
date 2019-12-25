package com.example.javabasic.jmm;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * 原子更新引用  AtomicStampedReference 解决ABA问题
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-15 09:05
 **/
public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    //解决ABA问题的原子版本类
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference(100,1);
    public static void main(String[] args) {
        System.out.println("===============ABA问题的产生=================");
         new Thread(() -> {
             atomicReference.compareAndSet(100, 101);
             atomicReference.compareAndSet(101, 100);
         },"thread1" ).start();

         //线程暂停一秒，确保线程1出现一次 ABA问题
        new Thread(() -> {
            try {
                SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 2019)+"\t" + atomicReference.get());
        },"thread2" ).start();


        try {
            SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("===============ABA问题的解决=================");

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第一次版本号："+stamp);

            //暂停1秒，确保线程四拿到同一版本的对象
            try { SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            atomicStampedReference.compareAndSet(100, 101, stamp, stamp+1);
            System.out.println(Thread.currentThread().getName()+"\t第二次版本号："+atomicStampedReference.getStamp());

            atomicStampedReference.compareAndSet(101, 100, stamp+1, stamp+2);
            System.out.println(Thread.currentThread().getName()+"\t第三次版本号："+atomicStampedReference.getStamp());

        },"thread3" ).start();


        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第一次版本号："+stamp);
            try { SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}

            boolean b = atomicStampedReference.compareAndSet(101, 100, stamp + 1, stamp + 2);
            System.out.println("修改是否成功"+b+"，当前最新值："+atomicStampedReference.getReference());
            System.out.println("成功预期的版本号"+stamp+"，实际版本号"+atomicStampedReference.getStamp());
        },"thread3" ).start();
    }
}
