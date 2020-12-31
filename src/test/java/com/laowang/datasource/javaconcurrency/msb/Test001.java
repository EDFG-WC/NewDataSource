package com.laowang.datasource.javaconcurrency.msb;

import java.util.concurrent.TimeUnit;

public class Test001 {
    public static void main(String[] args) {
        new T1().start();
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }
    }
}
