package com.zempty.atomic.demo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author zempty
 * @create 2019-05-06 11:24 AM
 **/
public class AtomicReferenceDemo {

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(2, 2);

    private static AtomicReference<User> atomicReference = new AtomicReference<>();

//    操作的字段必须是对 atomicReferenceFieldUpdater 可见的
//    操作的字段不能是static类型
//    操作的字段不能是final类型的，因为final根本没法修改
//    字段必须是volatile修饰的，也就是数据本身是读一致的
    private static AtomicReferenceFieldUpdater<User,String> atomicReferenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(User.class,String.class,"name");


    public static void main(String[] args) throws InterruptedException {

//       AtomicReference 操作实体类的引用
        User user1 = new User();
        user1.setAge(18);
        user1.setName("zempty");
        atomicReference.set(user1);
        System.out.println(atomicReference.get());
        User user2 = new User();
        user2.setName("test");
        user2.setAge(28);
        atomicReference.compareAndSet(user1, user2);
        System.out.println(atomicReference.get());

//      通过多线程操作 atomicInteger
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(
                    () -> {
                        int newValue = atomicInteger.incrementAndGet();
                        System.out.println(newValue);
                    }
            );
            t.start();
            t.join();

        }
        System.out.println(atomicInteger.get());
//   测试 atomicStampRerence

        for (int j = 0; j < 5; j++) {
            final int a = j;
            Thread t =new Thread(
                    ()->{
                boolean test = atomicStampedReference.compareAndSet(a, a+1, a, a+1);
                if(test == false)
                    System.out.println("修改值失败"+" : " +atomicStampedReference.getReference()+" ;--->"+atomicStampedReference.getStamp());
            });
            t.start();
            t.join();
        }
        System.out.println(atomicStampedReference.getStamp());
        System.out.println(atomicStampedReference.getReference());

        // AtomicReferenceFieldUpdater 的使用
        atomicReferenceFieldUpdater.set(user2,"test980000");
        System.out.println(atomicReferenceFieldUpdater.get(user2));
        atomicReferenceFieldUpdater.compareAndSet(user2, user2.getName(), "test7890");
        System.out.println(atomicReferenceFieldUpdater.get(user2));

    }




}
