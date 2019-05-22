package com.zempty.juc.lockSupport;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    private static Thread mainThread;

    public static void main(String[] args) {

        mainThread = Thread.currentThread();
        new Thread(new Task()).start();
        LockSupport.park();
        System.out.println();
        System.out.println("主线程执行完毕！");



//        test for 语句的使用,for语句没有括号，针对一条语句进行循环
        int j = 5;

        for (int i = 0;  i != j; i++)
//            if(i==4)
//                System.out.println(i);
            System.out.println(i);

//        有时候 if 括号中的语句不仅可以作为判断条件还可以作为代码去执行
        if (test()) {
            System.out.println("进入运行");
        }

    }

    public static boolean test() {
        System.out.println("test。。。。。。。。。。。");
        return false;
    }

   static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"开始执行");
            LockSupport.unpark(mainThread);
        }
    }


}
