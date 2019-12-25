package com.example.javabasic.gc;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-20 19:44
 * 在java中，可作为GC Roots对象的有
 * 1.虚拟机栈（栈帧中的本地变量表）中引用的对象
 * 2.方法区中静态属性引用的对象
 * 3.方法区中常量引用的对象
 * 4.本地方法栈中JMI（native方法）中引用的对象
 **/
public class GCRootDemo {
    private byte[] byteArray = new byte[100*1024*1024];
    //private static  GCRootDemo2 t2;
    //常量引用（强引用）
    //private static final GCRootDemo3 t3 = new GCRootDemo3(8);

    public static void m1(){
        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("完成第一次gc");
    }

    public static void main(String[] args) {
        m1();
    }
}
