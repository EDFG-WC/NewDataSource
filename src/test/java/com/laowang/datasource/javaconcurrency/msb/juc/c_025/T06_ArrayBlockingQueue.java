package com.laowang.datasource.javaconcurrency.msb.juc.c_025;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class T06_ArrayBlockingQueue {
    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);
    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strs.put("a" + i);
        }
        /**
         * 这里比较三个添加方法在队列容量满员情况下的异同:
         * 1.put方法会一直阻塞(用reentrantlock锁的)
         * 2.add方法会抛出异常
         * 3.offer什么都不做
         */
        // strs.put("aaa");
        // strs.add("aaa");
        // strs.offer("aaa");
        strs.offer("aaa", 1, TimeUnit.SECONDS);
        System.out.println(strs);
    }
}
