package com.laowang.datasource.JavaConcurrency.chapter7;

public class Bank3 {
    public static void main(String[] args) {
        //逻辑和线程控制分开:
        final Runnable ticketWindow = new SynchronizedRunnable();
        Thread windowThread1 = new Thread(ticketWindow, "一号窗");
        Thread windowThread2 = new Thread(ticketWindow, "二号窗");
        Thread windowThread3 = new Thread(ticketWindow, "三号窗");
        windowThread1.start();
        windowThread2.start();
        windowThread3.start();
    }
}
