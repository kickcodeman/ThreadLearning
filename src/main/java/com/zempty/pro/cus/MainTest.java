package com.zempty.pro.cus;

/**
 * @author zempty
 * @create 2019-05-02 5:06 PM
 **/
public class MainTest {
    public static void main(String[] args) {
        Depot depot = new Depot(100);
        Produce produce = new Produce(depot);
        Customer customer = new Customer(depot);
        depot.setCurrentSize(0);
        customer.consume(50);
        produce.produce(30);
    }
}
