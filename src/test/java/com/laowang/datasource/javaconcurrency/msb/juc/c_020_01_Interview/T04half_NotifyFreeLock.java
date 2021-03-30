package com.laowang.datasource.javaconcurrency.msb.juc.c_020_01_Interview;

import java.util.ArrayList;
import java.util.List;

public class T04half_NotifyFreeLock {

    List list = new ArrayList();

    public void add(Object obj) {
        list.add(obj);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        T04half_NotifyFreeLock task = new T04half_NotifyFreeLock();
        final Object lock = new Object();
    new Thread(
            () -> {
              synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " 启动");
                if (task.size() != 5) {
                  System.out.println("size还没到5 本线程暂停");
                  try {
                    lock.wait();
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                }
                System.out.println(Thread.currentThread().getName() + " 结束");
                lock.notify();
              }
            },
            "T2")
        .start();
        new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " 启动");
                for (int i = 0; i < 10; i++) {
                    task.add(new Object());
                    System.out.println("add " + i);
                    if (task.size() == 5) {
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        },"T1").start();
    }
}
