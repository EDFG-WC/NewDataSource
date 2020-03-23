package com.laowang.datasource.javaconcurrency.phase2.chapter19;

public final class ActiveObjectFactory {
    private ActiveObjectFactory() {
    }

    public static ActiveObject createActiveObject() {
        Servant servant = new Servant();
        ActivationQueue queue = new ActivationQueue();
        ScheduleThread scheduleThread = new ScheduleThread(queue);
        ActiveObjectProxy proxy = new ActiveObjectProxy(scheduleThread, servant);
        scheduleThread.start();
        return proxy;
    }
}
