package com.zempty.pro.cus;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zempty
 * @create 2019-05-02 4:26 PM
 **/
@Setter
@Getter
public class Depot {
    private int storage = 0;
    private int currentSize = 0;
    public Depot(int storage) {
        this.storage = storage;
    }

    public synchronized void produce(int val) {
        try {
            int left = val;
            while (left > 0) {
//                如果当前存货大于容量
                while(currentSize >= storage ){
//              停止生产操作,先消费
                    System.out.println("当前库存已经满了！！！，请先消费！");
                    wait();
                }
//                需要生产的储量
                left = currentSize + left > storage ? storage - currentSize : left;
//           开始不断投入生产中
                currentSize += 1;
                left -= 1;
//           有存货可以进行消费了,激活消费者进行消费
                notifyAll();
            }
            System.out.println("当前存货是 ："+currentSize);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public synchronized void consume(int val) {
        int left = val;
        try {
            while(left >0 ){
                while (currentSize <= 0) {
                    System.out.println("当前没有库存，请先生产！！！");
                    wait();
                }
                left = left > currentSize ? currentSize : left;
                currentSize-=1;
                left -= 1;
                notifyAll();
            }
            System.out.println("当前存货是：" + currentSize);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
