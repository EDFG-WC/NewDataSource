package com.laowang.datasource.javaconcurrency.phase2.chapter1;

/**
 * 饿汉式单例
 */
public class SingletonObject1 {
    /**
     * 它的缺点是不能懒加载, 只要调用这个类就会加载这个对象
     */
    private static final SingletonObject1 instance = new SingletonObject1();

    /**
     * 私有化构造函数
     */
    private SingletonObject1() {
    }

    public static SingletonObject1 getInstance() {
        return instance;
    }
}
