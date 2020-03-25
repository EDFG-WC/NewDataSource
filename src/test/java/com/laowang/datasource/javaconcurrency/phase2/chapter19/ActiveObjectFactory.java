package com.laowang.datasource.javaconcurrency.phase2.chapter19;

/**
 * 用工厂模式, 创建"主动对象"
 */
public final class ActiveObjectFactory {
    private ActiveObjectFactory() {
    }

    public static ActiveObject createActiveObject() {
        Servant servant = new Servant();
        ActivationQueue queue = new ActivationQueue();
        SchedulerThread schedulerThread = new SchedulerThread(queue);
        ActiveObjectProxy proxy = new ActiveObjectProxy(schedulerThread, servant);
        schedulerThread.start();
        return proxy;
    }
}
