package com.example.javabasic.juc;

import java.util.concurrent.TimeUnit;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;

/**
 * @Description: 深入理解8锁问题
 * @Author: zhangchao
 * @Date: 2019-11-03 21:05
 * 1.标准访问，先打印邮件还是短信
 * 2.邮件方法暂停4s，先打印邮件还是短信
 * 3.新增一个普通方法hello(),先打印邮件还是hello
 * 4.两部手机，先打印邮件还是短信？
 * 5.两个静态同步方法，同一手机，先打印什么
 * 6.两个静态同步方法，2部手机，先打印什么
 * 7.1个普通同步方法，1个静态同步方法，1部手机，？？？
 * 8.1个普通同步方法，1个静态同步方法，2部手机，先打印什么
 **/
class Phone
{
    public synchronized void sendEmail() throws Exception
    {

        out.println(currentThread().getName() + "\t sendEmail。。。");
    }

    public synchronized void sendMessage() throws Exception
    {
        out.println(currentThread().getName() + "\t sendMessage。。。");
    }

}

class Phone2
{
    public synchronized void sendEmail() throws Exception
    {
        try { TimeUnit.SECONDS.sleep(4);} catch (InterruptedException e) {e.printStackTrace();}

        out.println(currentThread().getName() + "\t sendEmail。。。");
    }

    public synchronized void sendMessage() throws Exception
    {
        out.println(currentThread().getName() + "\t sendMessage。。。");
    }

}
class Phone3
{
    public synchronized void sendEmail() throws Exception
    {
        try { TimeUnit.SECONDS.sleep(4);} catch (InterruptedException e) {e.printStackTrace();}

        out.println(currentThread().getName() + "\t sendEmail。。。");
    }

    public synchronized void sendMessage() throws Exception
    {
        out.println(currentThread().getName() + "\t sendMessage。。。");
    }
    public  void hello() throws Exception
    {
        out.println(currentThread().getName() + "\t hello。。。");
    }

}

class Phone4
{
    public static synchronized void sendEmail() throws Exception
    {
        try { TimeUnit.SECONDS.sleep(4);} catch (InterruptedException e) {e.printStackTrace();}

        out.println(currentThread().getName() + "\t sendEmail。。。");
    }

    public static synchronized void sendMessage() throws Exception
    {
        out.println(currentThread().getName() + "\t sendMessage。。。");
    }

}

class Phone5
{
    public synchronized void sendEmail() throws Exception
    {
        try { TimeUnit.SECONDS.sleep(4);} catch (InterruptedException e) {e.printStackTrace();}

        out.println(currentThread().getName() + "\t sendEmail。。。");
    }

    public static synchronized void sendMessage() throws Exception
    {
        out.println(currentThread().getName() + "\t sendMessage。。。");
    }

}
public class Lock8 {
    public static void main(String[] args) throws Exception{
        //m1();
        //m2();
        //m3();
        //m4();
        //m5();
        //m6();
        //m7();
        m8();
    }

    /**
     * AA	 sendEmail。。。
     * BB	 sendMessage。。。
     *
     * synchronized，同一时间只能一个线程进来，访问其中一个synchronized方法
     */
    static void m1() {
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA" ).start();
        new Thread(() -> {
            try {
                phone.sendMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"BB" ).start();
    }

    /**
     * AA	 sendEmail。。。
     * BB	 sendMessage。。。
     */
    static void m2() {
        Phone2 phone = new Phone2();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA" ).start();
        new Thread(() -> {
            try {
                phone.sendMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"BB" ).start();
    }

    /**
     * BB	 hello。。。
     * AA	 sendEmail。。。
     */
    static void m3() {
        Phone3 phone = new Phone3();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA" ).start();
        new Thread(() -> {
            try {
                phone.hello();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"BB" ).start();
    }

    /**
     * BB	 sendMessage。。。
     * AA	 sendEmail。。。
     */
    static void m4() {
        Phone3 phone = new Phone3();
        Phone3 phone2 = new Phone3();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA" ).start();

        new Thread(() -> {
            try {
                phone2.sendMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"BB" ).start();
    }

    /**
     * AA	 sendEmail。。。
     * BB	 sendMessage。。。
     */
    static void m5() {
        Phone4 phone = new Phone4();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA" ).start();
        new Thread(() -> {
            try {
                phone.sendMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"BB" ).start();
    }

    /**
     * AA	 sendEmail。。。
     * BB	 sendMessage。。。
     * 锁的是static模板类（可以理解成锁的是.class文件,而不加static则锁的是具体那个示例  ）
     */
    static void m6() {
        Phone4 phone = new Phone4();
        Phone4 phone2 = new Phone4();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA" ).start();
        new Thread(() -> {
            try {
                phone2.sendMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"BB" ).start();
    }

    /**
     * BB	 sendMessage。。。
     * AA	 sendEmail。。。
     */
    static void m7() {
        Phone5 phone = new Phone5();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA" ).start();
        new Thread(() -> {
            try {
                phone.sendMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"BB" ).start();
    }

    /**
     * BB	 sendMessage。。。
     * AA	 sendEmail。。。
     */
    static void m8() {
        Phone5 phone = new Phone5();
        Phone5 phone2 = new Phone5();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA" ).start();
        new Thread(() -> {
            try {
                phone2.sendMessage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"BB" ).start();
    }

}
