package com.zempty;

/**
 * @author zempty
 * @create 2019-05-02 3:39 PM
 **/
public class InterruptTask extends Thread {

    @Override
    public void run() {
        try {
            int i = 0;
            while (!this.isInterrupted()) {
                System.out.println("当前运行的线程是 ：" + Thread.currentThread().getName());
                Thread.sleep(1000);
                System.out.println(i++);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        InterruptTask task = new InterruptTask();
        task.start();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.interrupt();
    }

}
