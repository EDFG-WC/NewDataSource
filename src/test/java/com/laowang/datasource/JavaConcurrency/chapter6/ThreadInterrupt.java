package com.laowang.datasource.JavaConcurrency.chapter6;

public class ThreadInterrupt {
    private static final Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {

                }
            }
        };
        thread.start();
        Thread main = Thread.currentThread();
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                main.interrupt();
                System.out.println("interrupt");
            }
        };
        thread2.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
