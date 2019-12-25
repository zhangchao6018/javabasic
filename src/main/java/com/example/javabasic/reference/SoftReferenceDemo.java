package com.example.javabasic.reference;

import java.lang.ref.SoftReference;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-21 22:42
 **/
public class SoftReferenceDemo {

    public static void softRef_Memory_Enough(){
        Object obj = new Object();
        SoftReference<Object> softReference = new SoftReference<>(obj);
        System.out.println(obj);
        System.out.println(softReference.get());

        obj = null;
        System.gc();

        System.out.println(obj);
        System.out.println(softReference.get());

    }

    /**
     * jvm 配置 故意产生大对象并配置小内存
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRef_Memory_NotEnough(){
        Object obj = new Object();
        SoftReference<Object> softReference = new SoftReference<>(obj);
        System.out.println(obj);
        System.out.println(softReference.get());

        obj = null;
        try {
            byte [] byteArray = new byte[50*1024*1024];

        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            System.out.println(obj);
            System.out.println(softReference.get());
        }


    }

    public static void main(String[] args) {
        softRef_Memory_NotEnough();
    }
}
