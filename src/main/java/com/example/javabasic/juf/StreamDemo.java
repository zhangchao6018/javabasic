package com.example.javabasic.juf;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-11-05 08:36
 *
 *  题目：请按照给出数据，找出同时满足
 *  *      偶数ID且年龄大于24且用户名转为大写且用户名字母倒排序
 *  *      最后只输出一个用户名字
 **/
public class StreamDemo {
    public static void main(String[] args) {
        User u1= new User(11,"a",23);
        User u2= new User(12,"b",24);
        User u3= new User(13,"c",22);
        User u4= new User(14,"d",28);
        User u5= new User(16,"e",26);
        List<User> list = Arrays.asList(u1,u2,u3,u4,u5);

        list.stream().filter(u ->
        {return u.getId() % 2 == 0;}).filter(t ->
        {return t.getAge()>24;}).map(m->
        {return m.getUserName().toUpperCase();}).sorted((o1,o2) ->
        {return o2.compareTo(o1);}).limit(1)
        .forEach(System.out::println);




        List<Integer> list2 = Arrays.asList(1, 2, 3);
        list2=list2.stream().map(t->{return t*2;}).collect(Collectors.toList());
        for (Integer integer : list2) {
            System.out.println(integer);
        }
        /*//1
        Function<String,Integer> function1 = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return 111;
            }
        };
        Function<String ,Integer> function = s -> {return s.length();};
        System.out.println(function.apply("abc"));
        //2
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return false;
            }
        };
        //lambda 表达式写法
        Predicate<String> predicate1 = s ->{return s.isEmpty();};
        System.out.println(predicate1.test("xiatian"));
        //3
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {

            }
        };
        Consumer consumer1 = s->{
            System.out.println(s);
        };

        //4
        Supplier<String> supplier =new Supplier<String>() {
            @Override
            public String get() {
                return null;
            }
        };
        Supplier<String> supplier1 = () ->{return "aaaaa";};
        System.out.println(supplier1.get());*/
    }
}

interface MyInterface
{
    public int myInt(int x);
    public int myString(String str);
    public boolean isOk(String str);
}