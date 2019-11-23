package com.laowang.datasource.javaconcurrency.phase1.chapter9;

import java.util.stream.Stream;

public class DifferenceBetweenWaitAndSleep {
    private final static Object LOCK = new Object();

    public static void main(String[] args) {
        Stream.of("T1", "T2").forEach(name -> {
            new Thread(name) {
                @Override
                public void run() {
                    m2();
                }
            }.start();
        });
    }

    private static void m1() {
        synchronized (LOCK) {
            try {
                System.out.println("The thread " + Thread.currentThread().getName() + " enter");
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void m2() {
        synchronized (LOCK) {
            try {
                System.out.println("The thread " + Thread.currentThread().getName() + " enters");
                LOCK.wait();
                System.out.println("窝窝头, 一块钱四个!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
