package com.laowang.datasource.javaconcurrency.phase1.chapter10;

public class SynchronizedProblem {
    public static void main(String[] args) throws InterruptedException {
        new Thread("T1") {
            @Override
            public void run() {
                SynchronizedProblem.run();
            }
        }.start();

        Thread.sleep(1_000);

        Thread t2 = new Thread(() -> {
            SynchronizedProblem.run();
        }, "T2");
        t2.start();
        Thread.sleep(2_000);
        t2.interrupt();
        System.out.println(t2.isInterrupted());
    }

    private synchronized static void run() {
        System.out.println(Thread.currentThread().getName() + " runs");
        while (true) {
        }
    }
}
