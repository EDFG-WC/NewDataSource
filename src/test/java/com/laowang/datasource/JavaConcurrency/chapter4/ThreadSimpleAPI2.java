package com.laowang.datasource.JavaConcurrency.chapter4;

import java.util.Optional;

public class ThreadSimpleAPI2 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Optional.of(Thread.currentThread().getName() + "-INDEX " + i).ifPresent(System.out::println);
            }
        });
        thread1.setPriority(Thread.MAX_PRIORITY);
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Optional.of(Thread.currentThread().getName() + "-INDEX " + i).ifPresent(System.out::println);
            }
        });
        thread2.setPriority(Thread.NORM_PRIORITY);
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Optional.of(Thread.currentThread().getName() + "-INDEX " + i).ifPresent(System.out::println);
            }
        });
        thread3.setPriority(Thread.MIN_PRIORITY);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
