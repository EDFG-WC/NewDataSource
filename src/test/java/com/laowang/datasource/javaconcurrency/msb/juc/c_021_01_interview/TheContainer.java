package com.laowang.datasource.javaconcurrency.msb.juc.c_021_01_interview;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class TheContainer<T> {
    final private LinkedList<T> list = new LinkedList<T>();
    final private int MAX = 10;
    private int count = 0;

    private synchronized void put(T t) {
        while (list.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        ++count;
        this.notifyAll();
    }

    private synchronized T get() {
        T t = null;
        while (list.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = list.removeFirst();
        count--;
        // 一旦叫醒全部其他线程. 有可能叫醒其他生产者.
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        TheContainer<String> container = new TheContainer<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(container.get());
                }
            }, "consumer" + i).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++)
                    container.put(Thread.currentThread().getName() + " " + j);
            }, "producer" + i).start();
        }
    }
}
