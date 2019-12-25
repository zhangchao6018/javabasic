package com.example.javabasic.juf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-11-05 08:36
 **/
@Accessors(chain = true)
@Data
@AllArgsConstructor
public class User {
    private Integer id;
    private String userName;
    private Integer age;

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();

    }
}
