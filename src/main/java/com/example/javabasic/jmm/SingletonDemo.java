package com.example.javabasic.jmm;

import static java.lang.String.valueOf;

/**
 * 单例模式---volitile 关键字的使用场景
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-13 22:00
 **/
public class SingletonDemo {
    //加上volatile关键字
    private volatile static SingletonDemo instance= null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName() + "我是构造方达");
    }

    public  static  SingletonDemo getInstance(){
        if (instance==null){
            instance = new SingletonDemo();
        }
        return instance;
    }

    /**
     * 如果单例模式DCL代码（双短检索机制）存在隐患（由于多线程指令重排，有小几率造成线程安全问题）
     * @return
     */
    public  static  SingletonDemo getInstanceDCL(){
        if (instance==null){
            synchronized (SingletonDemo.class){
                if (instance==null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
    /*
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
*/

        /**
         * 多线程条件按下 单例模式出问题了
         * 虽然在SingletonDemo.getInstance 方法上加synchronized可以解决，但是性能浪费不可取
         */
      /*   for (int i=1;i<=20 ;i++)
          {
              new  Thread(() -> {
                  System.out.println(Thread.currentThread().getName() + "------"+SingletonDemo.getInstance());
             }, valueOf(i)).start();
         }
*/
        System.out.println("以下是DCl解决线程安全问题");
        for (int i=1;i<=20 ;i++)
        {
            new  Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "------"+SingletonDemo.getInstanceDCL());
            }, valueOf(i)).start();
        }
    }

}
