package com.example.javabasic.oom;

import static java.lang.String.valueOf;

/**
 * @Description:
 * @Author: zhangchao
 * @Date: 2019-10-22 21:36
 **/
public class UnableCreateNativeThreadDemo {
    /**
     * *********i=8166
     * 8165
     * Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
     * 	at java.lang.Thread.start0(Native Method)
     * 	at java.lang.Thread.start(Thread.java:717)
     * 	at com.example.javabasic.oom.UnableCreateNativeThreadDemo.main(UnableCreateNativeThreadDemo.java:21)
     *
     * Process finished with exit code 130 (interrupted by signal 2: SIGINT)
     * @param args
     */

    /**注意(进程需要强制终结)
     * ----------------------会导致电脑死机----------------
     * @param args
     */
    public static void main(String[] args) {
         for (int i=1; ; i++)
          {
              System.out.println("*********i=" +i);
              new  Thread(() -> {
                  System.out.println(Thread.currentThread().getName());
                  //try { TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);} catch (InterruptedException e) {e.printStackTrace();}

             }, valueOf(i)).start();
         }
    }
}
