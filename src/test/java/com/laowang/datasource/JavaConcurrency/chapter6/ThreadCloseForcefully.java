package com.laowang.datasource.JavaConcurrency.chapter6;

import java.util.Locale;

public class ThreadCloseForcefully {
    public static void main(String[] args) {
        ThreadService service = new ThreadService();
        long startTime = System.currentTimeMillis();
        service.execute(() -> {
//            while (true) {
//
//            }
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        Thread.currentThread().interrupt();
        service.shuntdown(10_000);
        long endTime = System.currentTimeMillis();
        System.out.println(String.format(Locale.ENGLISH, "Time cost is: %d ms", endTime - startTime));
    }
}
