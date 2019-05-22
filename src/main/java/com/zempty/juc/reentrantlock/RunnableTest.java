package com.zempty.juc.reentrantlock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author zempty
 * @create 2019-05-08 6:08 PM
 **/
public class RunnableTest implements Runnable{
    private Lock lock;
    private Condition condition;
    private CountDownLatch countDownLatch;
    public RunnableTest(Lock lock,CountDownLatch countDownLatch) {
        this.lock = lock;
        this.condition = lock.newCondition();
        this.countDownLatch = countDownLatch;
    }

    public Condition getCondition() {
        return this.condition;
    }
    @Override
    public void run() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始拿到锁执行！");
            countDownLatch.countDown();
            condition.await();
            System.out.println(Thread.currentThread().getName()+"被主线程唤醒！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
