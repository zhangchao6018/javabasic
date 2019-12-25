package com.example.javabasic.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-11-05 23:31
 **/
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName() +"没有返回值，完成了操作");
        });
        completableFuture.get();

        CompletableFuture<Integer> completableFuture2=CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() +"有返回值，完成了操作");
            int age = 10/0;
            return 1024;
        });

        System.out.println(completableFuture2.whenComplete((t, u) -> {
            System.out.println("------t:" + t);
            System.out.println("------u:" + u);
        }).exceptionally(f -> {
            System.out.println("-----excption:" + f.getMessage());
            return 23242134;
        }).get());

    }

}
