package com.laowang.datasource.javaconcurrency.msb.juc.c_020_01_Interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class T05half_CountDownLatch {
    List list = new ArrayList();

    public void add(Object o) {
        list.add(o);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        T05half_CountDownLatch lock = new T05half_CountDownLatch();
        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 启动");
            try {
                latch1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("" + latch2.getCount());
            latch2.countDown();
            System.out.println(Thread.currentThread().getName() + " 结束");
        }, "T2").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 启动");
            for (int i = 0; i < 10; i++) {
                lock.add(new Object());
                System.out.println("add " + i);
                if (lock.size() == 5) {
                    latch1.countDown();
                    try {
                        latch2.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "T1").start();
    }
}
