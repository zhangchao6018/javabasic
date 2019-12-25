package com.example.javabasic.oom;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-22 08:54
 *
 * 这个异常考的较多
 * 主要由nio引起
 *
 **/
public class DirectBufferMemory {
    /**
     *
     * 配置：
     * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
     * Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
     * @param args
     */
    public static void main(String[] args) {
        //本地内存
        System.out.println("初始配置的maxDirectMemory："+(sun.misc.VM.maxDirectMemory()/(double)1024/1024)+"MB");
        try { TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        // -XX:MaxDirectMemorySize=5m   配置最大为5m  实际new出6m
        Buffer bb = ByteBuffer.allocateDirect(6*1024*1024);


    }
}
