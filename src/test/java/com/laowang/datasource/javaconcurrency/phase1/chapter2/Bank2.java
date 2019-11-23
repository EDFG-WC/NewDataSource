package com.laowang.datasource.javaconcurrency.phase1.chapter2;

public class Bank2 {

    private final static int MAX = 50;

    public static void main(String[] args) {
        //逻辑和线程控制分开:
        final Runnable ticketWindow = () -> {
            int index = 0;
            while (index <= MAX) {
                System.out.println(Thread.currentThread() + " 的号码是: " + index++);
            }
        };
        Thread windowThread1 = new Thread(ticketWindow, "一号窗");
        Thread windowThread2 = new Thread(ticketWindow, "二号窗");
        Thread windowThread3 = new Thread(ticketWindow, "三号窗");
        windowThread1.start();
        windowThread2.start();
        windowThread3.start();
    }
}
