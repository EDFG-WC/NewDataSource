package com.laowang.datasource.javaconcurrency.msb;

import java.util.concurrent.TimeUnit;

public class T1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1");
        }
    }
}
