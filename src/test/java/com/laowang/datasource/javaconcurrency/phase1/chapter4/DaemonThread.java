package com.laowang.datasource.javaconcurrency.phase1.chapter4;

public class DaemonThread {
    public static void main(String[] args) {
        try {
            // 线程new状态
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + " running!");
                        Thread.sleep(100000);
                        System.out.println(Thread.currentThread().getName() + " done!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            // 把thread设为守护线程, 守护线程的状态就会变得不重要, 只要main结束, thread就会立即结束.
            thread.setDaemon(true);
            //runnable->running->dead | blocked
            thread.start();
            Thread.sleep(5_000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
