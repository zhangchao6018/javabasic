package com.example.javabasic.producer_comsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-19 20:42
 *
 *  volatile/CAS/atomicInteger/BlockQueue/线程交互/原子引用
 **/
class MyResource
{
    //默认开启，生产+消费者
    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws InterruptedException {
        String data = null;
        boolean retValue;
        while (FLAG)
        {
            data = atomicInteger.incrementAndGet()+"";
            retValue = blockingQueue.offer(data, 2l, SECONDS);
            if (retValue){
                out.println(currentThread().getName() + "\t 插入队列蛋糕"+data + "成功");
            }else {
                out.println(currentThread().getName() + "\t 插入队列蛋糕"+data + "失败");
            }
            SECONDS.sleep(1) ;
        }
        out.println(currentThread().getName() + "\t 大老板叫停了，表示FLAG=false，生产动作结束");

    }

    public void myConsumer() throws InterruptedException {
        String result = null;
        while (FLAG)
        {
            result = blockingQueue.poll(2l, SECONDS);
            if (null == result || result.equalsIgnoreCase(""))
            {
                FLAG = false;
                out.println(currentThread().getName() + "\t 超过2秒没有取到蛋糕，消费退出");
                out.println();
                out.println();
                return;
            }
            out.println(currentThread().getName() + "\t 消费队列蛋糕"+ result + "成功");
        }
    }

    public void stop()
    {
        this.FLAG=false;
    }
}
public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args)
    {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));

        new Thread(() -> {
            out.println(currentThread().getName() + "\t 生产线程启动 ");
            try {
                myResource.myProd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"prod" ).start();

        new Thread(() -> {
            out.println(currentThread().getName() + "\t 消费线程启动 ");
            try {
                myResource.myConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"consumer" ).start();

        try { SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
        out.println();
        out.println();
        out.println();
        out.println("5秒钟时间到，大老板叫停，活动结束");

        myResource.stop();
    }

}
