package com.laowang.datasource.javaconcurrency.msb.juc.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class T13_TestLockSupport {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 5) {
                    LockSupport.park();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
        // 只要unpark过, 再park也不会停下线程了.
//        LockSupport.unpark(t);

        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after 8 seconds!");
        LockSupport.unpark(t);
    }
}
