package com.laowang.datasource.javaconcurrency.phase3;

import lombok.SneakyThrows;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimeScheduler {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("=======" + System.currentTimeMillis());
                // 问题: schedule的第二个参数就是延迟若干秒执行一次, 但如果我在这里延迟超过这个参数, 会发生什么?
                TimeUnit.SECONDS.sleep(10);
            }
        };
        timer.schedule(task, 1000, 1000);
    }
}
