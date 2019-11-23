package com.laowang.datasource.javaconcurrency.phase1.chapter8;

public class DeadLockTest {
    public static void main(String[] args) {
        OtherService otherService = new OtherService();
        DeadLock deadLock = new DeadLock(otherService);
        otherService.setDeadLock(deadLock);
        new Thread(()->{
            while (true) {
                deadLock.method1();
            }
        }).start();

        new Thread(()->{
            while (true) {
                otherService.s2();
            }
        }).start();
    }
}
