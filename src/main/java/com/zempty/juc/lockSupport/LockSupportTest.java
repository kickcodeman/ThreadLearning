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

    }

   static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"开始执行");
            LockSupport.unpark(mainThread);
        }
    }


}
