package com.laowang.datasource.javaconcurrency.phase2.chapter18;

import java.util.Random;

/**
 * 把半成品放到传送带的线程
 */
public class TransportThread extends Thread {
    private final Channel channel;
    private static final Random random = new Random(System.currentTimeMillis());

    public TransportThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override public void run() {
        try {
            for (int index = 0; true; index++) {
                Request request = new Request(getName(), index);
                this.channel.put(request);
                Thread.sleep(random.nextInt(1_000));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
