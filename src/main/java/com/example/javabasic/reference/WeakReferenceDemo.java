package com.example.javabasic.reference;

import java.lang.ref.WeakReference;

/**
 * @Description: 只要有gc就回收
 * @Author: zhangchao
 * @Date: 2019-10-21 23:28
 **/
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object obj = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(obj);
        System.out.println(obj);
        System.out.println(weakReference.get());

        System.out.println("=========================");
        obj=null;
        System.gc();
        System.out.println(obj);
        System.out.println(weakReference.get());

    }
}
