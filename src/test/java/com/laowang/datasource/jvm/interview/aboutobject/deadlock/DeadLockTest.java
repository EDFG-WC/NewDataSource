package com.laowang.datasource.jvm.interview.aboutobject.deadlock;

public class DeadLockTest {
    public static class DeadLock {
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

    public static class OtherService {
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

    public static void main(String[] args) {
        OtherService otherService = new OtherService();
        DeadLock deadLock = new DeadLock(otherService);
        otherService.setDeadLock(deadLock);
        new Thread(() -> {
            while (true) {
                deadLock.method1();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                otherService.s2();
            }
        }).start();
    }
}
