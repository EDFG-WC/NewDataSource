package com.laowang.datasource.javaconcurrency.phase1.chapter8;

public class OtherService {

    private DeadLock deadLock;

    private final Object lock = new Object();

    public void s1() {
        synchronized (lock) {
            System.out.println("s1==============");
        }
    }

    public void s2() {
        synchronized (lock) {
            System.out.println("s2==============");
            deadLock.method2();
        }
    }

    public void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }
}
