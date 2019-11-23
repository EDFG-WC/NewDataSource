package com.laowang.datasource.javaconcurrency.phase1.chapter3;

import java.util.Arrays;

public class CreateThread2 {
    public static void main(String[] args) {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
//        System.out.println(Thread.currentThread().getName());
//        System.out.println(Thread.currentThread().getThreadGroup().getName());
//        System.out.println(t.getThreadGroup());
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
//        System.out.println(threadGroup.activeCount());
        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);

        /*for (Thread temp : threads) {
            System.out.println(temp);
        }*/
        Arrays.asList(threads).forEach(System.out::println);
    }
}
