package com.zempty.juc.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {


    private static CopyOnWriteArrayList<Integer> cowal = new CopyOnWriteArrayList();

//    private static ArrayList<Integer> arrayList = new ArrayList<>();

    public static void main(String[] args) {

//        如果多线程操作 ArrayList ,遍历的时候因为机制 fail-fast 会出现异常
        for (int j = 0; j < 2; j++) {
            new Thread(()->{
                for (int i = 0; i < 5; i++) {
                    cowal.add(i);
//                    arrayList.add(i);
                    printAll();
                }
            }).start();
        }
    }

    public static void printAll() {
        Iterator<Integer> iterator = cowal.iterator();
//        Iterator<Integer> iterator = arrayList.iterator();

        while (iterator.hasNext()) {
            System.out.print("--"+iterator.next());
        }
        System.out.println();
    }
}
