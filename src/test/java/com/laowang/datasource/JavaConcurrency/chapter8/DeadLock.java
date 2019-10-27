package com.laowang.datasource.JavaConcurrency.chapter8;

public class DeadLock {
    private OtherService otherService;

    public DeadLock(OtherService otherService) {
        this.otherService = otherService;
    }

    private final Object lock = new Object();

    public void method1(){
        synchronized (lock) {
            System.out.println("m1=========");
            otherService.s1();
        }
    }

    public void method2(){
        synchronized (lock) {
            System.out.println("m2=========");
        }
    }

    public void setOtherService(OtherService otherService) {
        this.otherService = otherService;
    }
}
