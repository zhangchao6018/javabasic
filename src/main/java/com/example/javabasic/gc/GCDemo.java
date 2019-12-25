package com.example.javabasic.gc;

import java.util.Random;

/**
 * @Description:垃圾收集器
 * @Author: zhangchao
 * @Date: 2019-10-23 07:48
 * 1 串行
 * 初始空间10m 最大10m  打印gc回收细节  打印默认垃圾收集器 激活串行gc
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC   （DefNew+Tenured）
 * 2 年轻并行，老年代串行
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC   （ParNew+Tenured）
 * 3 新生代 并行 与老年代相互激活
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC  （PsYoungGen+ParOldGen）
 * 4.老年代 并行 与新生代相互激活
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC （PsYoungGen+ParOldGen）
 * 5  不加就是默认 同3，4
 *
 * 6.重要：（微服务架构生产服务器 推荐）
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC （Par New generation+concurrent mark-sweep）
 * 7 serial old（msc jdk8后不能单独开启 ，但它是cms的兜底策略 保证系统不死）
 *
 * 8 G1
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
 *
 **/
public class GCDemo {
    public static void main(String[] args) {
        try {
            String str = "hello gc";
            while (true)
            {
                str += str + new Random().nextInt(7777777)+new Random().nextInt(88888888);
                str.intern();
            }

        }catch (Throwable e){
            e.printStackTrace();
        }
    }
}
