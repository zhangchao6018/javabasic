package com.example.javabasic.thread;

import java.util.concurrent.CountDownLatch;

import static java.lang.String.valueOf;

/**juc辅助类笔记
 * 倒计时器 加 枚举的使用
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-17 21:56
 **/
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //closeDoor();

        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i=1; i<=6; i++)
        {
            new  Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 国被灭");
                countDownLatch.countDown();
            }, CountryEnum.forEach_CountryEnum(i).getRetmessage()).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 秦国一统天下");
    }

    private static void closeDoor() throws InterruptedException {
        //保证上面执行完
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i=1; i<=20; i++)
         {
             new  Thread(() -> {
                 System.out.println(Thread.currentThread().getName() + "\t 同学离开教室");
                 countDownLatch.countDown();
            }, valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 班长锁门");
    }
}
