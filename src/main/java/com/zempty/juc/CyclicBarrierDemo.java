package com.zempty.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author zempty
 * @create 2019-05-13 2:38 PM
 **/
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(5,()->
                System.out.println("线程执行完毕！会执行该代码！")
                );
        System.out.println(Thread.currentThread().getName()+"开始执行！");
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"开始执行！");
                try {
                    barrier.await();
                    System.out.println(Thread.currentThread().getName()+"暂停后又开始执行了！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println(Thread.currentThread().getName()+"执行完毕！");
    }
}
