package com.zempty.juc;

import java.util.concurrent.Semaphore;

/**
 * @author zempty
 * @create 2019-05-13 3:22 PM
 **/
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10);
        Thread t1 = new Thread(new Task(semaphore,3));
        Thread t2 = new Thread(new Task(semaphore, 4));
        Thread t3 = new Thread(new Task(semaphore, 6));
        t1.start();
        t2.start();
        t3.start();
    }
}
