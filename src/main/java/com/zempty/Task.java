package com.zempty;

/**
 * @author zempty
 * @create 2019-05-02 10:03 AM
 **/
public class Task implements Runnable {


    public synchronized void joinTest() {
        System.out.println("测试主线程是如何获取锁的");
        try {
            System.out.println("=====================================");
            System.out.println(Thread.currentThread().getName());
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        synchronized (this) {
            System.out.println("当前运行的线程是 ： " +Thread.currentThread().getName());
            System.out.println("持有锁的对象是 :"+ this);
            try {
                System.out.println(" 唤醒沉睡的线程 .....");
                System.out.println(Thread.currentThread().getName() +"礼让之后接着执行！");
                notify();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" 执行完毕！！！");
        }
    }
}
