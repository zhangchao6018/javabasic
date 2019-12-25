package com.example.javabasic.juc;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-11-03 12:06
 *
 * lambda expression
 *    口诀
 *      拷贝小括号，写死右箭头，落地大括号
 *    @FunctionalInterface  //只要接口只有一个方法，默认该注解
 *    函数式接口可以有多个默认方法实现
 *    静态方法实现
 *
 **/
@FunctionalInterface  //只要接口只有一个方法，默认该注解
interface  Foo{
    public int sayHello(int x , int y);

    default int div(int x , int y){
        System.out.println("这是默认方法1");
        return x+y;
    };

    default int div2(int x , int y){
        System.out.println("这是默认方法2");
        return x+y;
    }
    public static int div3(int x , int y){
        System.out.println("这是静态方法");
        return x+y;
    }


}
public class lambdaExpressDemo {
    public static void main(String[] args) {
        new Foo() {
            @Override
            public int sayHello(int param1, int Param2) {
                return 0;
            }
        };

        Foo foo = (x,y) ->{
            System.out.println("hello");
            return x+y;
        };
        System.out.println(foo.sayHello(1, 2));
        System.out.println(foo.div(1, 2));
        System.out.println(foo.div2(1, 2));
        System.out.println(Foo.div3(1, 2));
    }
}
