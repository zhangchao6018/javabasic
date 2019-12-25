package com.example.javabasic.reference;

/**
 * @Description:强引用不会被垃圾回收器回收
 * @Author: zhangchao
 * @Date: 2019-10-21 22:32
 **/
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = o1;
        o1=null;
        System.gc();
        System.out.println(o2);
    }
}
