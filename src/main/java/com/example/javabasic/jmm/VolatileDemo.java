package com.example.javabasic.jmm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.valueOf;

/**
 * @Description:volatile 关键字特点学习
 * @Author: zhangchao
 * @Date: 2019-10-13 17:58
 **/

class MyData{
    volatile int number = 0;
    public void changeData(){
        this.number=60;
    }

    //加了volatile，不保证原子性
    public void changeDatapPlus(){
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    /**
     * AtomicInteger 可以解决-不保证原子性问题
     */
    public void changeAtomic(){
        atomicInteger.getAndIncrement();
    }
}

/**
 * 1.验证volatile的可见性  及时通知其他线程，主物理内存的值已经被其他线程修改
 * 如果没有使用valatile关键字修饰，主内存的数据在被某一线程修改后，其他线程不可见
 *
 * 2.volatile不保证原子性
 *      不可分割，完整性，也即某个线程在做某个具体业务的时候，中间不可以被加塞或者被分割，需要整体完整
 *      2.1.为什么不能保证原子性
 *      2.2 如何解决   synchronized（杀鸡宰牛刀，错误）
 *
 *
 */
public class VolatileDemo {

    public static void main(String[] args) {
        //seeOKByVolitile();
        MyData myData = new MyData();
         for (int i=1;i<=20 ;i++)
          {
              new  Thread(() -> {
                 for (int j=1;j<=1000 ;j++)
                 {
                     myData.changeDatapPlus();
                     myData.changeAtomic();

                 }
             }, valueOf(i)).start();
         }

         //等待上面20个线程都算完后，取得最终的结果值（默认main线程和jc线程）
        while (Thread.activeCount() >2 )
        {
            Thread.yield();
        }
        /**
         * 结果mainfinal number is18735
         *
         */
        System.out.println(Thread.currentThread().getName() + "final number is" + myData.number);
        System.out.println(Thread.currentThread().getName() + "final atomic number is" + myData.atomicInteger);
    }

    private static void seeOKByVolitile() {
        MyData myData = new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"come in");
            //暂停一会儿线程
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.changeData();
            System.out.println(Thread.currentThread().getName()+"number value："+myData.number);
        }).start();

        //第二个线程(主线程)
        while (myData.number == 0){
            //一直等待
        }
        System.out.println(Thread.currentThread().getName()+"task over");
    }
}
