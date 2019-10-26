package com.laowang.datasource.JavaConcurrency.chapter6;

public class ThreadInterrupt3 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        System.out.println("收到打断信号");
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
