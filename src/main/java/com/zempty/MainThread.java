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
//        设置线程处于终端状态
        Thread.currentThread().interrupt();
//        查看线程的状态
        System.out.println(Thread.currentThread().isInterrupted());

//        返回线程的中断状态，同时线程状态清零
        System.out.println(Thread.interrupted());
        System.out.println(Thread.currentThread().isInterrupted());

//        如果线程没有处于中断状态，返回false
        System.out.println(Thread.interrupted());

        System.out.println(Thread.currentThread().isInterrupted());

        synchronized (task) {
            try {
                Thread t1 = new Thread(task,"1");
                Thread t2 = new Thread(task,"2");
                t2.setPriority(10);
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
