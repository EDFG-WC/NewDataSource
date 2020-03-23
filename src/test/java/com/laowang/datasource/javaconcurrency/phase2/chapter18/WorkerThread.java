package com.laowang.datasource.javaconcurrency.phase2.chapter18;

import java.util.Random;

/**
 * 加工半成品, 从传送带上把产品拿走.
 */
public class WorkerThread extends Thread {
    private final Channel channel;
    private static final Random random = new Random(System.currentTimeMillis());

    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override public void run() {
        while (true) {
            try {
                channel.take().execute();
                Thread.sleep(random.nextInt(1_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
