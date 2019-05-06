package com.zempty.atomic.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zempty
 * @create 2019-05-03 4:35 PM
 **/
@Setter
@Getter
@ToString
public class User {
    public User(){
        System.out.println("user 构造方法被调用");
    }
    private String name;
    private int age;
    private static String id="USER_ID";
}
