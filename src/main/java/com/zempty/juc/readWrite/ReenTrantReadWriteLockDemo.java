package com.zempty.juc.readWrite;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zempty
 * @create 2019-05-13 3:37 PM
 **/
public class ReenTrantReadWriteLockDemo {
    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Account account = new Account(readWriteLock, 1000);
        for (int i = 0; i < 5; i++) {
            account.getCash();
            account.setCash(1000+i);
        }
    }
}
