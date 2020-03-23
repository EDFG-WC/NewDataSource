package com.laowang.datasource.javaconcurrency.phase2.chapter18;

public class WorkerClient {
    public static void main(String[] args) {
        final Channel channel = new Channel(5);
        channel.start2Work();
        new TransportThread("Alex", channel).start();
        new TransportThread("Jack", channel).start();
        new TransportThread("Bill", channel).start();
        new TransportThread("Lily", channel).start();
    }
}
