package com.zempty.juc;

import java.util.concurrent.Semaphore;

/**
 * @author zempty
 * @create 2019-05-13 3:18 PM
 **/
public class Task implements Runnable {
    private Semaphore semaphore;
    private int holder;

    public Task(Semaphore semaphore, int holder) {
        this.semaphore = semaphore;
        this.holder = holder;
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"开始进来了！");
        try {
            semaphore.acquire(holder);
            System.out.println(Thread.currentThread().getName()+"正在执行！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release(holder);
            System.out.println(Thread.currentThread().getName()+"运行结束了！");
        }
    }
}
