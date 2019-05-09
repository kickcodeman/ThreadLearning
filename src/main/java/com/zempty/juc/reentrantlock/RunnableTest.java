package com.zempty.juc.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author zempty
 * @create 2019-05-08 6:08 PM
 **/
public class RunnableTest implements Runnable{
    private Lock lock;
    private Condition condition;
    public RunnableTest(Lock lock) {
        this.lock = lock;
        condition = lock.newCondition();
    }

    public Condition getCondition() {
        return this.condition;
    }
    @Override
    public void run() {
        lock.lock();
        try {
            condition.await();
            System.out.println(Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
