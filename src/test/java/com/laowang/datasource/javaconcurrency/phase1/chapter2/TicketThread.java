package com.laowang.datasource.javaconcurrency.phase1.chapter2;

public class TicketThread implements Runnable {
    int tickets = 100;
    @Override
    public void run() {
        while (true) {
            synchronized (TicketThread.class) {
                if (tickets > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + tickets--);
                } else {
                    break;
                }
            }
        }
    }
}

class test {
    public static void main(String[] args) {
        TicketThread t1 = new TicketThread();
        Thread thread = new Thread(t1);
        thread.start();
        Thread thread2 = new Thread(t1);
        thread2.start();
    }
}