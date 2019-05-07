package com.zempty.pro.cus;

/**
 * @author zempty
 * @create 2019-05-02 5:03 PM
 **/
public class Customer {
    private Depot depot;

    public Customer(Depot depot) {
        this.depot = depot;
    }

    public void consume(int val) {
        new Thread(()->{
            depot.consume(val);
        }).start();
    }


    public static void main(String[] args) {
        int b = test(false);
        System.out.println(b);
    }

//    该方法同线程知识无关主要是为了弄清楚三目运算符，先运算后赋值
    public static int test(boolean success) {
        int a = success ? 1 : 2 ;
        return a;
    }

}
