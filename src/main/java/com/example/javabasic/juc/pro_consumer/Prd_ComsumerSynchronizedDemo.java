package com.example.javabasic.juc.pro_consumer;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-11-03 18:09
 * 最原始synchronized 方法
 **/
class ShareResource
{
    private int number=0;

    public synchronized void increase() throws InterruptedException {
        //判断
        while (number!=0){
            this.wait();
        }
        //干活
        number++;
        out.println(currentThread().getName() + "\t 执行+，结果："+number);

        //唤醒
        this.notifyAll();
    }
    public synchronized void decrease () throws InterruptedException {
        //判断
        while (number==0){
            this.wait();
        }
        //干活
        number--;
        out.println(currentThread().getName() + "\t 执行-，结果："+number);

        //唤醒
        this.notifyAll();
    }

}
public class Prd_ComsumerSynchronizedDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
             for (int i=1; i<=10; i++){
                 try {
                     shareResource.increase();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
        },"AA" ).start();

        new Thread(() -> {
            for (int i=1; i<=10; i++){
                try {
                    shareResource.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB" ).start();
    }
}
