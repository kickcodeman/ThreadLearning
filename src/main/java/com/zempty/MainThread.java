package com.zempty;

/**
 * @author zempty
 * @create 2019-05-02 10:04 AM
 **/
public class MainThread {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"开始运行！");
        Task task = new Task();
        System.out.println("当前的任务对象是 :"+task);

        synchronized (task) {
            try {
                Thread t1 = new Thread(task);
                Thread t2 = new Thread(task);
                t1.start();
                t2.start();

                System.out.println(Thread.currentThread().getName()+"主线程释放锁");
                task.wait();
                System.out.println(Thread.currentThread().getName()+" 主线程拿到锁执行完毕！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
