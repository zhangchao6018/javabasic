package com.example.javabasic.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache
{
    private  volatile Map<String , Object> map = new HashMap<>();

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    //private Lock lock = new ReentrantLock();

    public void put(String key , Object value)
    {
        rwLock.writeLock().lock();
        try
        {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入：" + key);
            //暂停一会儿线程
            try { TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
            map.put(key,value );
            System.out.println(Thread.currentThread().getName() + "\t 写入完成： " + key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwLock.writeLock()  .unlock();
        }



    }

    public void get(String key )
    {
        rwLock.readLock().lock();
        try
        {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取：" + key);
            //暂停一会儿线程
            try { TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {e.printStackTrace();}
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成： " + result );

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwLock.readLock()  .unlock();
        }


    }

    public void clearMap ()
    {
        map.clear();
    }
}
/**
 * @Description:
 *
 * 读- 读 能共存（优点：读操作不会锁住）
 * 读-写 不能共存
 * 写-写 不能共存
 *
 * 写操作： 原子+独占，中间整个过程必须是一个完整的统一体，不许被分割打断
 * @Author: zhangchao
 * @Date: 2019-10-17 19:47
 **/
public class ReadWritelockDemo {


    public static void main(String[] args) {
        MyCache myCache = new MyCache();

         for (int i=1; i<=20; i++)
          {
              final int tempInt = i;
              new Thread(() -> {
                    myCache.put(tempInt+"", tempInt+"");
             }, String.valueOf(i)).start();
         }

        for (int i=1; i<=20; i++)
        {
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt+"");
            }, String.valueOf(i)).start();
        }
    }
}
