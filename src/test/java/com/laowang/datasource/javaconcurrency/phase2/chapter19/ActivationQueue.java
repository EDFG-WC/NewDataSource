package com.laowang.datasource.javaconcurrency.phase2.chapter19;

import java.util.LinkedList;

/**
 * 按顺序保存MethodRequest对象的类
 */
public class ActivationQueue {
    private static final int MAX_METHOD_REQUEST_QUEUE_SIZE = 100;
    private final LinkedList<MethodRequest> methodQueue;

    public ActivationQueue() {
        methodQueue = new LinkedList<>();
    }

    public synchronized void put(MethodRequest methodRequest) {
        try {
            while (methodQueue.size() >= MAX_METHOD_REQUEST_QUEUE_SIZE) {
                this.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.methodQueue.addLast(methodRequest);
        this.notifyAll();
    }

    public synchronized MethodRequest take() {
        try {
            while (methodQueue.isEmpty()) {
                this.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        MethodRequest methodRequest = methodQueue.removeFirst();
        this.notifyAll();
        return methodRequest;
    }
}
