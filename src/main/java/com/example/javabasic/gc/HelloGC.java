package com.example.javabasic.gc;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-20 21:34
 *
 * 1.jps -l
 *      查看指定进程
 *
 * 2.jinfo
 * 2.1布尔类型
 *      jinfo -flag PrintGCDetails 94026    查看指定进程的某个gc参数是否激活，值是多少
 *      如何配置：-XX:+PrintGCDetails
 * 2.2KV类型
 *       jinfo -flag MetaspaceSize 94107  查看元空间大小
 *          -XX:MetaspaceSize=21807104
 *       如何配置：-XX:MetaspaceSize=128m
 *
 *       jinfo -flag MaxTenuringThreshold 94317  新生代到老年代的计数器次数
 *       MaxTenuringThreshold=15
 *
 *       jsp
 *  3.盘点初始化jvm参数
 *  3.1java -XX:+PrintFlagsInitial  查看初始化参数
 *
 *  3.2java -XX:+PrintFlagsFinal -version 查看最终参数
 *          eg： bool PrintFlagsFinal                          := true                                {product}
 *          有"："表示被人为修改过（人为或者jvm修改过）
 *
 *  3.3 java -XX:PrintFlagsFinal -XX:MetaspaceSize=512m T  修改值
 *
 *  3.4 java -XX:+PrintCommandLineFlags -version  看垃圾回收器默认设置
 *
 *-XX:InitialHeapSize=536870912 -XX:MaxHeapSize=8589934592 -XX:+PrintCommandLineFlags -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseParallelGC
 * java version "1.8.0_181"
 * Java(TM) SE Runtime Environment (build 1.8.0_181-b13)
 * Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)

 **/
public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        //1.手动输入查看修改jvm参数(看上面笔记)
        //jvmParam();

        //2.程序查看jvm部分参数
        //jdkGetParm();

        //3.输出Gc收集日志信息
        /**
         * 修改vm参数
         * -Xms10m -Xmx10m -XX:+PrintGCDetail
         *
         * 产生垃圾回收
         */
        //byte[] byteData = new byte[50*1024*1024];

        //4.设置新生代中 eden 和 S0/S1 空间比例
        /**
         * -XX:+PrintGCDetails -XX:+UseSerialGC -Xms10m -Xmx10m -XX:SurvivorRatio=8
         * 默认8:1:1 跟-XX:SurvivorRatio=8配置结果一样
         */
        System.out.println("********* hello GC");

        /**
         * 设置垃圾的最大年龄
         *
         * -XX:MaxTenuringThreshold=12   (java8 取值0-15)
         */
    }

    static void jdkGetParm() {
        long totalMemory = Runtime.getRuntime().totalMemory();//虚拟机内存总量
        long maxMemory = Runtime.getRuntime().maxMemory();//虚拟机试图使用的最大内存量
        System.out.println("totalMemory"+"\t" +totalMemory+"字节，"+(double)totalMemory/1024/1024+"m");
        System.out.println("maxMemory"+"\t" +maxMemory+"字节，"+(double)maxMemory/1024/1024+"m");
    }

    static void jvmParam() throws InterruptedException {
        System.out.println("88888888888HelloGC");
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
