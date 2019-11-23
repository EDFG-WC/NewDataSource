package com.laowang.datasource.javaconcurrency.phase1.chapter6;

public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (isInterrupted()) {
                        break;
                    }
                }
            }
        };
        thread1.start();
        Thread main = Thread.currentThread();
        //启动一个新线程去打断thread
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
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
