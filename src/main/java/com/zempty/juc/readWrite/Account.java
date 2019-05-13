package com.zempty.juc.readWrite;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * @author zempty
 * @create 2019-05-13 3:38 PM
 **/
public class Account {
    private ReadWriteLock readWriteLock;
    private int cash;

    public Account(ReadWriteLock readWriteLock, int cash) {
        this.readWriteLock = readWriteLock;
        this.cash = cash;
    }

    public void setCash(int cash) {
        new Thread(()->{
            readWriteLock.writeLock().lock();
            try {
                System.out.println(Thread.currentThread().getName()+"开始执行设置");
                this.cash = cash;
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName()+"设置 cash 为："+cash);
                readWriteLock.writeLock().unlock();
            }
        }).start();
    }

    public void getCash() {
        new Thread(()->{
            readWriteLock.readLock().lock();
            try {
                System.out.println(Thread.currentThread().getName()+"开始执行读取");
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName()+"读取到的 cash 为：" +cash);
                readWriteLock.readLock().unlock();
            }
        }).start();
    }
}
