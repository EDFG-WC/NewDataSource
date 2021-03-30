package com.laowang.datasource.javaconcurrency.msb.juc.c_020_01_Interview;

public class Test09half_A1B2C3D4 {
    public static void main(String[] args) {
        int size = 26;
        Object LOCK = new Object();
        new Thread(() -> {
            for (int i = 0; i < size; i++) {
                synchronized (LOCK) {
                    System.out.print((char)(65 + i));
                    try {
                        LOCK.notify();
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "AlphaBeta").start();

        new Thread(() -> {
            for (int i = 0; i < size; i++) {
                synchronized (LOCK) {
                    System.out.print(i + 1);
                    try {
                        LOCK.notify();
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "AlphaBeta").start();
    }
}
