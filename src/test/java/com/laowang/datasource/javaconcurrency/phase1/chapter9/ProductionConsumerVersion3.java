package com.laowang.datasource.javaconcurrency.phase1.chapter9;

import java.util.stream.Stream;

public class ProductionConsumerVersion3 {
    private int i = 1;

    final private Object LOCK = new Object();

    // 新关键字
    private volatile boolean beenProduced = false;

    private void produce() {
        synchronized (LOCK) {
            // 为什么用while不用if: 如果2个线程同时到达这里, 用if的情况, 第一个线程抢到锁会执行wait, 然后执行下面的代码, 而第二个线程会直接执行下面的代码
            while (beenProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
            System.out.println("P-->" + i);
            // 生产, 并通知他人消费
            LOCK.notifyAll();
            beenProduced = true;
        }
    }

    private void consume() {
        synchronized (LOCK) {
            while (!beenProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("C-->" + (i));
            LOCK.notifyAll();
            beenProduced = false;
        }
    }

    public static void main(String[] args) {
        ProductionConsumerVersion3 pc = new ProductionConsumerVersion3();
        Stream.of("P1", "P2").forEach(n -> {
            new Thread(() -> {
                while (true) {
                    pc.produce();
                }
            },n).start();
        });

        Stream.of("C1", "C2").forEach(n -> {
            new Thread(() -> {
                while (true) {
                    pc.consume();
                }
            },n).start();
        });
    }
}
