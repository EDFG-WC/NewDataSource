package com.laowang.classloader;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ClassActiveUse {

    public static void main(String[] args) throws ClassNotFoundException {
        //        new Obj(); // 主动使用. 初始化该类对象
        //        System.out.println(I.value); // 主动使用
        //        System.out.println(Obj.salary); // 非主动使用 final修饰的对象放在常量池. 不会触发类加载
        //        Obj.printSalary(); // 主动使用
        //        Class.forName("com.laowang.classloader.Obj"); // 主动使用
        //        System.out.println(Child.age); // 对Obj和Child来说是主动使用
        //        System.out.println(Child.tax); // 父类初始化, 但子类不会被初始化, 对子类来说不算主动使用. static修饰的成员变量不算该类对象
        //        Obj[] arrays = new Obj[10]; // 以对象为模版创建数组, 不算主动使用
        //        System.out.println(Obj.x); // salary在编译期直接拿到结果了. 不需要初始化Obj, 但x不初始化是得不到结果的.
        final Boolean map = Obj.map;
        System.out.println(map);
    }
}

class Obj {
    // 访问final修饰的静态变量时, 不会触发类加载, 因为已经在编译期放在常量池了
    public static final long salary = 100000L;
    public static final int x = new Random().nextInt(100);
    public static long tax = 156L;
    public static final boolean map = true;
    public static final char c = 'c';

    //为什么没有被执行?
    static {
        System.out.println("Obj 初始化.");
    }

    public static void printSalary() {
        System.out.println("====");
    }
}

class Child extends Obj {
    // 被final修饰之后, 这个变量在编译期就已经在常量池了, 直接引用甚至不会初始化这个类.
    public static int age = 32;

    static {
        System.out.println("child was initialized.");
    }
}

interface I {
    int value = 1;
}



