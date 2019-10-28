package com.laowang.datasource.JavaConcurrency.chapter9;

import java.util.stream.Stream;

public class ProductionConsumerVersion2 {
    private int i = 1;

    final private Object LOCK = new Object();

    // 新关键字
    private volatile boolean beenProduced = false;

    private void produce() {
        synchronized (LOCK) {
            if (beenProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println("P-->" + i);
                // 生产, 并通知他人消费
                LOCK.notify();
                beenProduced = true;
            }
        }
    }

    private void consume() {
        synchronized (LOCK) {
            // 消费, 并通知他人生产
            if (beenProduced) {
                System.out.println("C-->" + (i));
                LOCK.notify();
                beenProduced = false;
            } else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProductionConsumerVersion2 pc = new ProductionConsumerVersion2();
        Stream.of("P1", "P2").forEach(n -> {
            new Thread(() -> {
                while (true) {
                    pc.produce();
                }
            }).start();
        });

        Stream.of("C1", "C2").forEach(n -> {
            new Thread(() -> {
                while (true) {
                    pc.consume();
                }
            }).start();
        });
    }
}
