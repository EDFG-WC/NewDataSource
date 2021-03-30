package com.laowang.datasource.javaconcurrency.phase1.chapter6;

import java.util.Locale;

public class ThreadCloseForcefully {
    public static void main(String[] args) {
        ThreadService service = new ThreadService();
        long startTime = System.currentTimeMillis();
        Thread.currentThread().setName("nihao");
        service.execute(() -> {
            // 假设这次异步任务永远无法完成
            while (true) {
            }
            // 这次异步任务在规定时间内完成了
            /* try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        });
        // Thread.currentThread().interrupt();
        service.shutdown(10_000);
        long endTime = System.currentTimeMillis();
        System.out.printf(Locale.ENGLISH, "Time cost: %d ms%n", endTime - startTime);
    }
}
