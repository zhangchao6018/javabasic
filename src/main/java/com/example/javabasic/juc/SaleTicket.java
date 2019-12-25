package com.example.javabasic.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.String.valueOf;

//资源类 传统方式
class Ticket
{
    private int number=30;
    public synchronized void saleTicket()
    {
        if (number>0)
        {
            System.out.println(Thread.currentThread().getName()+"\t  卖出第"+ (number--)+"还剩"+""+number);
        }
    }

}
//juc方式
class Ticket2
{
    private int number=30;
    private Lock lock = new ReentrantLock ();
    public  void saleTicket()
    {
        //......这里可以写不需要锁的代码
        lock.lock();
        try
        {
            if (number>0)
            {
                System.out.println(Thread.currentThread().getName()+"\t  卖出第"+ (number--)+"还剩"+""+number);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

}
/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-11-03 11:09
 **/
public class SaleTicket {
    public static void main(String[] args) {
        //传统解决线程安全问题
        //traditionalMethod();
        jucMethod();


    }

    static void traditionalMethod() {
        Ticket ticket = new Ticket();
          new Thread(()->{for (int i=1; i<=20; i++) ticket.saleTicket();},"A").start();
         for (int i=1; i<=20; i++)
          {
              new  Thread(() -> {

             }, valueOf(i)).start();
         }

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1; i<=30; i++){
                    ticket.saleTicket();
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1; i<=30; i++){
                    ticket.saleTicket();
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1; i<=30; i++){
                    ticket.saleTicket();
                }
            }
        },"C").start();
    }
    static void jucMethod() {
        Ticket2 ticket = new Ticket2();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1; i<=30; i++){
                    ticket.saleTicket();
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1; i<=30; i++){
                    ticket.saleTicket();
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=1; i<=30; i++){
                    ticket.saleTicket();
                }
            }
        },"C").start();
    }

}
