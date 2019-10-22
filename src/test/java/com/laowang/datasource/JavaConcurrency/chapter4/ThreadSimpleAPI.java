package com.laowang.datasource.JavaConcurrency.chapter4;

import java.util.Optional;

public class ThreadSimpleAPI {
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            Optional.of("Hello").ifPresent(System.out::println);
            try {
                Thread.sleep(100_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");
        Optional.of(thread.getName()).ifPresent(System.out::println);
        Optional.of(thread.getId()).ifPresent(System.out::println);
        Optional.of(thread.getPriority()).ifPresent(System.out::println);
        thread.start();
    }
}
