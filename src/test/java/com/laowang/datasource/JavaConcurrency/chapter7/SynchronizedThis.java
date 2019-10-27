package com.laowang.datasource.JavaConcurrency.chapter7;

public class SynchronizedThis {
    public static void main(String[] args) {
        ThisLock thisLock = new ThisLock();
        new Thread("T1") {
            @Override
            public void run() {
                thisLock.method1();
            }
        }.start();

        new Thread("T2") {
            @Override
            public void run() {
                thisLock.method2();
            }
        }.start();
    }
}

class ThisLock {
    private final Object LOCK = new Object();

    public synchronized void method1() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void method2() {
        synchronized (LOCK) {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
