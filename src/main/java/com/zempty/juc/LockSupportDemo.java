package com.zempty.juc;

import java.util.concurrent.locks.LockSupport;

/**
 * @author zempty
 * @create 2019-05-13 2:19 PM
 **/
public class LockSupportDemo {

    private static Thread mainThread;

    public static void main(String[] args) {
        mainThread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+" 将会被阻塞！");

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"开始执行！");
            LockSupport.unpark(mainThread);
        }).start();

        LockSupport.park();
        System.out.println(Thread.currentThread().getName()+"恢复执行！");
    }
}
