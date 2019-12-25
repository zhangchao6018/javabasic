package com.example.javabasic.oom;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-22 22:04
 **/
public class MetaspaceOOMDemo {
    static class  OOMTest{}
    /**
     *-XX:MetaspaceSize=15m -XX:MaxMetaspaceSize=15m
     *
     ---------第1153后发生异常
     org.springframework.cglib.core.CodeGenerationException: java.lang.OutOfMemoryError-->Metaspace
     at org.springframework.cglib.core.ReflectUtils.defineClass(ReflectUtils.java:530)
     at org.springframework.cglib.core.AbstractClassGenerator.generate(AbstractClassGenerator.java:363)
     at org.springframework.cglib.proxy.Enhancer.generate(Enhancer.java:582)
     at org.springframework.cglib.core.AbstractClassGenerator$ClassLoaderData.get(AbstractClassGenerator.java:131)
     at org.springframework.cglib.core.AbstractClassGenerator.create(AbstractClassGenerator.java:319)
     at org.springframework.cglib.proxy.Enhancer.createHelper(Enhancer.java:569)
     at org.springframework.cglib.proxy.Enhancer.create(Enhancer.java:384)
     at com.example.javabasic.oom.MetaspaceOOMDemo.main(MetaspaceOOMDemo.java:57)
     Caused by: java.lang.OutOfMemoryError: Metaspace
     at java.lang.ClassLoader.defineClass1(Native Method)
     at java.lang.ClassLoader.defineClass(ClassLoader.java:763)
     at sun.reflect.GeneratedMethodAccessor1.invoke(Unknown Source)
     at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     at java.lang.reflect.Method.invoke(Method.java:498)
     at org.springframework.cglib.core.ReflectUtils.defineClass(ReflectUtils.java:527)
     ... 7 more
     Disconnected from the target VM, address: '127.0.0.1:49829', transport: 'socket'

     *
     * @param args
     */
    public static void main(String[] args) {
        int i = 0;
        try {
            while (true)
            {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o,args );
                    }
                });
                enhancer.create();
            }


        }catch (Throwable e){
            System.out.println("---------第"+i+"后发生异常");
            e.printStackTrace();
        }
    }
}
