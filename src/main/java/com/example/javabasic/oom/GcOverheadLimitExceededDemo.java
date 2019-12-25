package com.example.javabasic.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-22 08:44
 *
 * 直接内存大小修改成5m
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 * java.lang.OutOfMemoryError: GC overhead limit exceeded
 * 事倍功半 gc效果很差
 **/
public class GcOverheadLimitExceededDemo {
    public static void main(String[] args) {
        int i= 0;
        List<String> list = new ArrayList<>();
       try {
           while (true)
           {
               list.add(String.valueOf(++i).intern());
           }

       }catch (Throwable e){
           System.out.println("_________________________i:"+i);
           e.printStackTrace();
           throw  e;
       }finally {

       }
    }
}
