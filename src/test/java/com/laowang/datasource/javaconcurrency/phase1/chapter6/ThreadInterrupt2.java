package com.laowang.datasource.javaconcurrency.phase1.chapter6;

public class ThreadInterrupt2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(">>"+this.isInterrupted());
                    try {
                        Thread.sleep(1_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
        Thread.sleep(100);
        System.out.println(thread.isInterrupted());
        thread.interrupt();
        System.out.println(thread.isInterrupted());
    }
}
