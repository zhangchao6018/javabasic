package com.example.javabasic.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import static java.lang.String.valueOf;

/**
 * @Description:
 * 先到的先阻塞，直到集齐龙珠
 * @Author: zhangchao
 * @Date: 2019-10-18 07:50
 **/
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()->{System.out.println("***********召唤神龙"); });

         for (int i=1; i<=7; i++)
          {
              new  Thread(() -> {
                  System.out.println(Thread.currentThread().getName()+"\t 集齐了一颗龙珠");
                  try {
                      cyclicBarrier.await();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  } catch (BrokenBarrierException e) {
                      e.printStackTrace();
                  }
              }, valueOf(i)).start();
         }
    }
}
