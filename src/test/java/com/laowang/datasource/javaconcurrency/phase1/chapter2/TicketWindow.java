package com.laowang.datasource.javaconcurrency.phase1.chapter2;

public class TicketWindow extends Thread {
    private final String name;

    private final int MAX = 50;

    private static int index = 1;

    private static Object LOK = new Object();

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
            while (index <= MAX) {
                System.out.println("柜台: " + name + ", 当前号码是: " + index++);
            }
    }
}
