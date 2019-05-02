package com.zempty;
/**
 * @author zempty
 * @create 2019-05-02 1:37 PM
 **/
public class ThreadjoinTest {


    public static void main(String[] args) {
        Task task = new Task();
        System.out.println("主线程拿到 task 对象的锁，执行并将处于等待状态");
        task.joinTest();//这端代码主线程执行释放了锁，无法执行后续的代码。

        Thread t = new Thread(task);
        t.start();
        System.out.println("没有线程唤醒主线程，无法运行到这里！");
    }
}
