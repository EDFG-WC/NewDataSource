package com.laowang.datasource.javaconcurrency.phase1.chapter6;

public class ThreadInterrupt4 {
    private static final Object MONITOR = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    synchronized (MONITOR) {
                        // 使用wait必须使用synchronized
                        try {
                            MONITOR.wait(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
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
