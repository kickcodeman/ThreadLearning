package com.zempty.atomic.demo;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author zempty
 * @create 2019-05-03 4:37 PM
 **/
public class UnsafeDemo {

    public static void main(String[] args) throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        System.out.println("==================打印 Unsafe 的实体类================ ");
        System.out.println(unsafe);

        User user = (User) unsafe.allocateInstance(User.class);
        System.out.println("======================通过 Unsafe 实例化对象=================");
        System.out.println(user);

//        通过反射获取实体类的各个 Field
        Field name = User.class.getDeclaredField("name");
        Field age = User.class.getDeclaredField("age");
        Field id = User.class.getDeclaredField("id");

//       获取各个 field 的内存地址
        long nameAddress = unsafe.objectFieldOffset(name);
        long ageAddress = unsafe.objectFieldOffset(age);
        long idAddress = unsafe.staticFieldOffset(id);
        System.out.println("===================打印各个 Field 的 内存地址=====================");
        System.out.println(nameAddress);
        System.out.println(ageAddress);
        System.out.println(idAddress);

//        通过 Unsafe 给实体类赋值,这里也可以通过反射给实体类赋值
        unsafe.putInt(user, ageAddress, 28);
        unsafe.putObject(user, nameAddress, "zempty");
        System.out.println(user);
        System.out.println(unsafe.getObject(User.class, idAddress));
        unsafe.putObject(User.class, idAddress, "modify static field successfully!");
        System.out.println(unsafe.getObject(User.class, idAddress));

//        通过 Unsafe 分配内存,向内存写入数据
        long data = 1000;
        byte size = 1;
        long memoryAddress = unsafe.allocateMemory(size);
        System.out.println(memoryAddress);
        unsafe.putLong(memoryAddress,data);
        long testData = unsafe.getLong(memoryAddress);
        System.out.println(testData);

    }
}
