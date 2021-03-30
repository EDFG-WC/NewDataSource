package com.laowang.datasource.javaconcurrency.msb.juc.c_026_00_interview.A1B2C3;

/**
 * 分别打印字母和数字的2个线程最后都需要notify, 如果不这样, 肯定会有一个陷入阻塞. 所以最后要notify最后, 到最后唤醒主线程结束
 */
public class T06_00_sync_wait_notify {
    public static void main(String[] args) {
        final Object o = new Object();

        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(() -> {
            synchronized (o) {
                for (char c : aI) {
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait(); // 让出锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify(); // 必须，否则无法停止程序
            }

        }, "t1").start();

        new Thread(() -> {
            synchronized (o) {
                for (char c : aC) {
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }, "t2").start();
    }
}

// 如果我想保证t2在t1之前打印，也就是说保证首先输出的是A而不是1，这个时候该如何做？
