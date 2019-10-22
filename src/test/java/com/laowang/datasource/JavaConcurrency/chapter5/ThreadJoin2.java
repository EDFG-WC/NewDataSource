package com.laowang.datasource.JavaConcurrency.chapter5;

import java.util.Optional;
import java.util.stream.IntStream;

public class ThreadJoin2 {
    public static void main(String[] args) throws InterruptedException {
//        Thread thread1 = new Thread(() -> {
//            try {
//                System.out.println("t1 is running.");
//                Thread.sleep(1_000);
//                System.out.println("t1 is done");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        thread1.start();
//        thread1.join(100);
//        IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));
Thread.currentThread().join();    }
}
