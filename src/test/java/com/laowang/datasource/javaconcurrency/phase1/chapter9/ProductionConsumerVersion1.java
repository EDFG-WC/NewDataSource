package com.laowang.datasource.javaconcurrency.phase1.chapter9;

public class ProductionConsumerVersion1 {
    private int i = 1;

    final private Object LOCK = new Object();

    private void produce() {
        synchronized (LOCK) {
            System.out.println("P-->" + (i++));
        }
    }

    private void consume() {
        synchronized (LOCK) {
            System.out.println("C-->" + (i));
        }
    }

    public static void main(String[] args) {
        ProductionConsumerVersion1 pc = new ProductionConsumerVersion1();

        new Thread(() -> {
            while (true) {
                pc.produce();
            }
        }, "P").start();

        new Thread(() -> {
            while (true) {
                pc.consume();
            }
        }, "C").start();
    }
}
