package com.laowang.datasource.javaconcurrency.phase1.chapter7;

public class TicketWindowsRunnable implements Runnable {
    private int index = 0;
    private final static int MAX = 500;
    private final static Object LOCK = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (LOCK) {
                if (index > MAX) {
                    break;
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " 的号码是: " + index++);
            }
        }
    }
}
