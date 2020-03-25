package com.laowang.datasource.javaconcurrency.phase2.chapter19;

/**
 * 调用execute方法处理 MethodRequest对象的类
 */
public class SchedulerThread extends Thread {
    private final ActivationQueue activationQueue;

    public SchedulerThread(ActivationQueue activationQueue) {
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
