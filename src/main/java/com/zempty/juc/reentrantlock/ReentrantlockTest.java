package com.zempty.juc.reentrantlock;

import java.util.concurrent.CountDownLatch;
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
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RunnableTest runnableTest = new RunnableTest(lock,countDownLatch);
        Thread t1 = new Thread(runnableTest);
        t1.start();
//       阻止主线程先执行，子线程先执行
        countDownLatch.await();
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

