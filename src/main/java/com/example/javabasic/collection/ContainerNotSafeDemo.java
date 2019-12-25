package com.example.javabasic.collection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合类不安全问题
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-15 19:38
 **/
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        //listNotSaft();
        //setNotSafe();

        //Map<String, String> map = new HashMap<>();
        //解决线程安全问题
        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        //写时复制 todo hashMap 和 ConcurrentHashMap 底层区别
        //Map<String, String> map = new ConcurrentHashMap<>();

        for (int i=1;i<=3000 ;i++)
        {
            new  Thread(() -> {
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(Thread.currentThread().getName()+map );
            }, String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
        //Set<String> set = new HashSet<>();
        //解决线程安全问题
        //Set<String> set = Collections.synchronizedSet(new HashSet<>());
        //底层还是CopyOnWriteArrayList
        Set<String> set = new CopyOnWriteArraySet<>();
        //写时复制
        //List<String> list = new CopyOnWriteArrayList<>();

        for (int i=1;i<=3000 ;i++)
        {
            new  Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(Thread.currentThread().getName()+set );
            }, String.valueOf(i)).start();
        }
    }

    private static void listNotSaft() {
        //List<String> list = new ArrayList<>();
        //解决线程安全问题
        //List<String> list = new Vector<>();
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        //写时复制
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i=1;i<=3000 ;i++)
          {
              new  Thread(() -> {
                  list.add(UUID.randomUUID().toString().substring(0,8));
                  System.out.println(Thread.currentThread().getName()+list );
             }, String.valueOf(i)).start();
         }
        //java.util.ConcurrentModificationException  并发修改异常

        /**
         *
         *1.故障现象
         * java.util.ConcurrentModificationException
         *
         * 2.导致原因
         * 并发争抢修改，参考花名册，多人写，一个人正在写，其他人过来抢夺
         *
         * 3.解决方案
         *   3.1 n ew vector() 性能差
         *   3.2 List<String> list = Collections.synchronizedList(new ArrayList<>());
         *
         *
         * 4.优化建议（规避）
         *
         *
         *
         *
         *
         */}
}
