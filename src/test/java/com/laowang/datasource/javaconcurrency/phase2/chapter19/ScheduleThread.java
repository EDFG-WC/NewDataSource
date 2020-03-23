package com.laowang.datasource.javaconcurrency.phase2.chapter19;

public class ScheduleThread extends Thread {
    private final ActivationQueue activationQueue;

    public ScheduleThread(ActivationQueue activationQueue) {
        this.activationQueue = activationQueue;
    }

    public void invoke(MethodRequest request) {
        this.activationQueue.put(request);
    }

    @Override public void run() {
        while (true) {
            activationQueue.take().execute();
        }
    }
}
