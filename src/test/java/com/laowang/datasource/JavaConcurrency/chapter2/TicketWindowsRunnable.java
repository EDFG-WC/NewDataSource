package com.laowang.datasource.JavaConcurrency.chapter2;

import java.util.TreeMap;

public class TicketWindowsRunnable implements Runnable {
    private int index = 0;
    private final static int MAX = 50;

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println(Thread.currentThread() + " 的号码是: " + index++);
        }
    }
}
