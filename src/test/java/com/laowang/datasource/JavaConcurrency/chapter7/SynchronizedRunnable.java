package com.laowang.datasource.JavaConcurrency.chapter7;

public class SynchronizedRunnable implements Runnable {
    private int index = 0;
    private final static int MAX = 500;
    private final static Object LOCK = new Object();

    @Override
    public void run() {
        while (true) {
            if (saling()) {
                break;
            }
        }
    }

    private synchronized boolean saling() {
        if (index > MAX) {
            return true;
        }
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread() + " 的号码是: " + index++);
        return false;
    }
}
