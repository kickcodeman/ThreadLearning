package com.zempty.pro.cus;

/**
 * @author zempty
 * @create 2019-05-02 5:00 PM
 **/
public class Produce {
    private Depot depot;

    public Produce(Depot depot) {
        this.depot = depot;
    }

    public void produce(int val) {
        new Thread(()->{
            depot.produce(val);
        }).start();
    }
}
