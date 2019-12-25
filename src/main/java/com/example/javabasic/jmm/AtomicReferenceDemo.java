package com.example.javabasic.jmm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-15 08:25
 **/
@Getter
@ToString
@AllArgsConstructor
class User
{
    String userName;
    int age;
}


public class AtomicReferenceDemo {

    public static void main(String[] args)
    {
        User zhangsan =  new User("zhangsan" , 24);
        User lisi =  new User("li4" , 25);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(zhangsan);


        System.out.println(atomicReference.compareAndSet(zhangsan,lisi)+"\t"+atomicReference.get().toString());
        // false	User(userName=li4, age=25)
        System.out.println(atomicReference.compareAndSet(zhangsan,lisi)+"\t"+atomicReference.get().toString());

    }
}
