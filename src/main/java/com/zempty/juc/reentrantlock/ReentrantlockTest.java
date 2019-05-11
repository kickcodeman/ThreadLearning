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
        lock.lock();
        try {
            t1.start();
            System.out.println("上面的 t1 线程虽然已经执行，但是却无法获取到锁，锁已经被主线程拿到！ ");
            runnableTest.getCondition().await();//主线程调用 condition.await() 使主现程放弃锁 ，其他线程有机会拿到锁
            System.out.println("主线程重新拿到了锁，继续往下面进行执行。。。。。");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}

