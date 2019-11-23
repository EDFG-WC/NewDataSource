package com.laowang.datasource.javaconcurrency.phase1.chapter5;

import java.util.Optional;
import java.util.stream.IntStream;

public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));
        });
        Thread thread2 = new Thread(() -> {
            IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        Optional.of("All of tasks done").ifPresent(System.out::println);
        IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));
    }
}
