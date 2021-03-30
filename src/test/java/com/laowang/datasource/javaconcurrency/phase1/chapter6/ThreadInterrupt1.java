package com.laowang.datasource.javaconcurrency.phase1.chapter6;

public class ThreadInterrupt1 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
            }
        });
        thread.start();
        Thread.sleep(100);
        System.out.println(thread.isInterrupted());
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }
}
