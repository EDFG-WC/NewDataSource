package com.laowang.datasource.javaconcurrency.executor.test1;

public class CountTask implements Runnable {
    private Integer num = 0;

    @Override
    public void run() {
        while (num < 10000) {
            num++;
            System.out.println(Thread.currentThread().getName() + "===>" + num);
        }
    }
}
