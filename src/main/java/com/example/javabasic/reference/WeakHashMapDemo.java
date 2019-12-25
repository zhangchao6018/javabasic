package com.example.javabasic.reference;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-21 23:48
 **/
public class WeakHashMapDemo {
    public static void main(String[] args) {
        myHashMap();
        System.out.println("==================");
        myWeakHashMap();
    }

    private static void myHashMap(){
        //static class Node<K,V> implements Map.Entry<K,V> {  底层是Node节点的key value 键值对
        HashMap<Integer,String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value= "hashMap";
        map.put(key,value);
        System.out.println(map);
        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t "+map.size());
    }

    private static void myWeakHashMap(){
        WeakHashMap<Integer,String> map = new WeakHashMap<>();
        Integer key = new Integer(2);

        String value= "weakH ashMap";
        map.put(key,value);
        System.out.println(map);
        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t "+map.size());
    }
}
