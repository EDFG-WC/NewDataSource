package com.laowang.datasource.jvm.interview.aboutobject.deadlock;


public class DeadLock {
    private OtherService otherService;

    public DeadLock(OtherService otherService) {
        this.otherService = otherService;
    }

    private final Object lock = new Object();

    public void method1() {
        synchronized (lock) {
            System.out.println("m1=========");
            otherService.s1();
        }
    }

    public void method2() {
        synchronized (lock) {
            System.out.println("m2=========");
        }
    }
}
