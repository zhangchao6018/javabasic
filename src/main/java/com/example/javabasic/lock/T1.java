package com.example.javabasic.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-16 22:40
 **/
public class T1 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);
        lock.lock();
        lock.unlock();
    }
}
