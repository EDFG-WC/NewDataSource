package com.laowang.datasource.javaconcurrency.phase2.chapter19;

/**
 * 将方法调用转换为MethodRequest对象的类（实现了ActiveObject的接口）
 */
class ActiveObjectProxy implements ActiveObject {
    private final SchedulerThread schedulerThread;
    private final Servant servant;

    public ActiveObjectProxy(SchedulerThread schedulerThread, Servant servant) {
        this.schedulerThread = schedulerThread;
        this.servant = servant;
    }

    @Override public Result makeString(int count, char fillChar) {
        FutureResult futureResult = new FutureResult();
        schedulerThread.invoke(new MakeStringRequest(servant, futureResult, count, fillChar));
        return futureResult;
    }

    @Override public void displayString(String text) {
        schedulerThread.invoke(new DisplayStringRequest(servant, text));
    }
}
