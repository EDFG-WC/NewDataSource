package com.laowang.datasource.javaconcurrency.msb.juc.c_020_01_Interview;

public class T09_A1B2C3D4 {
    private static int size = 26;
    final static Object LOCK = new Object();

    public static void main(String[] args) {

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
        }, "charThread").start();

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
        }, "numThread").start();
    }
}
