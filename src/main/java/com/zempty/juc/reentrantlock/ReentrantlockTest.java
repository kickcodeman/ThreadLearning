package com.zempty.juc.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zempty
 * @create 2019-05-08 5:39 PM
 **/
public class ReentrantlockTest {

    public static Lock lock;
    public static void main(String[] args) throws InterruptedException {
        lock = new ReentrantLock();
        RunnableTest runnableTest = new RunnableTest(lock);
        Thread t1 = new Thread(runnableTest);
        t1.start();
        Thread.sleep(6000);
        lock.lock();
        try {
            runnableTest.getCondition().signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}

