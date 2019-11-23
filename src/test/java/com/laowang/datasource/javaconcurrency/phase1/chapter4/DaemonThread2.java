package com.laowang.datasource.javaconcurrency.phase1.chapter4;

public class DaemonThread2 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            Thread innerThread = new Thread(() -> {
                try {
                    // 假设它就是一个心跳检测
                    while (true) {
                        System.out.println("Health check thread: " + Thread.currentThread().getName() + " is running.");
                        Thread.sleep(1_000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            innerThread.setDaemon(true);
            innerThread.start();
            try {
                Thread.sleep(1_000);
                System.out.println(Thread.currentThread().getName() + " finished.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        thread.setDaemon(true);
        thread.start();
    }
}
