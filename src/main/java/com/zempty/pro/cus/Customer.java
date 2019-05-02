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

}
