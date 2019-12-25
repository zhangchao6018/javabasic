package com.example.javabasic.oom;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-22 08:23
 * StackOverflowError  错误
 *
 **/
public class StackOverflowErrorDemo {
    public static void main(String[] args) {
        stackOverflowError();
    }

    /**
     * Exception in thread "main" java.lang.StackOverflowError
     * 栈空间不足
     * 原因：深度的方法调用
     */
    private static void stackOverflowError(){
        stackOverflowError();

    }
}
