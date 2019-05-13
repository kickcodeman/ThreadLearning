package com.zempty.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author zempty
 * @create 2019-05-13 2:28 PM
 **/
public class CounDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        System.out.println(Thread.currentThread().getName()+"开始执行！");
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"开始执行！");
                countDownLatch.countDown();
            }).start();
        }
        System.out.println(countDownLatch.getCount());
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"执行完毕！");


    }
}
