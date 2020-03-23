package com.laowang.datasource.javaconcurrency.phase2.chapter18;

import java.util.Arrays;

/**
 * 模拟的是传送带. 异步化生产和消费2个步骤, 消除生产和消费速度差异引起的阻塞.
 */
public class Channel {
    private static final int MAX_REQUEST = 100;
    private final Request[] requestQueue;

    private int head;
    private int tail;
    private int count;

    private final WorkerThread[] workerPool;

    public Channel(int workers) {
        this.requestQueue = new Request[MAX_REQUEST];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.workerPool = new WorkerThread[workers];
        this.init();
    }

    private void init() {
        for (int index = 0; index < workerPool.length; index++) {
            workerPool[index] = new WorkerThread("Worker-" + index, this);
        }

    }

    /**
     * 传送带开始工作. 工人开始从传送带上搬走加工好的产品
     */
    public void start2Work() {
        Arrays.asList(workerPool).forEach(WorkerThread::start);
    }

    /**
     * 模拟往生产线上添加半成品
     *
     * @param request
     */
    public synchronized void put(Request request) {
        // requestQueue的长度是工人能加工办成品的上限
        while (count >= requestQueue.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 没达到上限就往队列里添加一个它的index肯定是tail
        this.requestQueue[tail] = request;
        // index=tail的位置上放了一个值, 然后把tail+1, 给下次放值就放在这里. 同时如果tail的值超过100, 我们自动让tail从0开始.
        this.tail = (tail + 1) % requestQueue.length;
        this.count++;
        // 唤醒线程, 开始干活
        this.notifyAll();
    }

    /**
     * 从队列里拿
     *
     * @return
     */
    public synchronized Request take() {
        while (count <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Request request = this.requestQueue[head];
        this.head = (this.head + 1) % requestQueue.length;
        this.count--;
        this.notifyAll();
        return request;
    }
}
