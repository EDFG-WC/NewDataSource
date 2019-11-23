package com.laowang.datasource.javaconcurrency.phase1.chapter12;

import java.util.Arrays;

public class ThreadGroupAPI {
    public static void main(String[] args) {
        //use the name
        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1, "T1") {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        t1.start();
        ThreadGroup tg2 = new ThreadGroup(tg1, "TG2");
        Thread t2 = new Thread(tg2, "TG2") {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1_000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        t2.start();
        System.out.println(tg1.activeCount());
        System.out.println(tg1.activeGroupCount());
        t2.checkAccess();
//        tg1.destroy();
        System.out.println("============================");
        Thread[] ts1 = new Thread[tg1.activeCount()];
        System.out.println(ts1.length);
        tg1.enumerate(ts1);
        Arrays.asList(ts1).forEach(System.out::println);
        System.out.println("============================");
        tg1.enumerate(ts1, true);
        Arrays.asList(ts1).forEach(System.out::println);

        System.out.println("============================");
        ts1 = new Thread[10];
        Thread.currentThread().getThreadGroup().enumerate(ts1, true);
        Arrays.asList(ts1).forEach(System.out::println);
        System.out.println("============================");
        ts1 = new Thread[10];
        Thread.currentThread().getThreadGroup().enumerate(ts1, false);
        Arrays.asList(ts1).forEach(System.out::println);

        System.out.println("============================");
//        tg1.interrupt();
        tg1.setDaemon(true);
    }
}
