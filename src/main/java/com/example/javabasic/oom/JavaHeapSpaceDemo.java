package com.example.javabasic.oom;

import java.util.Random;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-22 08:32
 **/
public class JavaHeapSpaceDemo {
    /**
     * 配置虚拟机参数  -Xms10m -Xmx10m
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     * 堆内存不足
     * 原因：对象太多太大 导致堆内存不足
     * @param args
     */
    public static void main(String[] args) {
        String str = "test";
        while (true)
        {
            str+=str + new Random().nextInt(11111111)+new Random().nextInt(22221122);
            str.intern();
        }
    }
}
