package com.example.javabasic.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1.什么是CAS compareAndSet 比较并交换
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-13 23:49
 **/
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        atomicInteger.getAndIncrement();
        System.out.println(atomicInteger.compareAndSet(5, 2019)+"\t current atomicInteger value : " + atomicInteger.get());
    }

}
