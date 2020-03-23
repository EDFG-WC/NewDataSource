package com.laowang.datasource.javaconcurrency.phase2.chapter19;

class ActiveObjectProxy implements ActiveObject {
    private final ScheduleThread scheduleThread;
    private final Servant servant;

    public ActiveObjectProxy(ScheduleThread scheduleThread, Servant servant) {
        this.scheduleThread = scheduleThread;
        this.servant = servant;
    }

    @Override public Result makeString(int count, char fillChar) {
        FutureResult futureResult = new FutureResult();
        scheduleThread.invoke(new MakeStringRequest(servant, futureResult, count, fillChar));
        return futureResult;
    }

    @Override public void displayString(String text) {
        scheduleThread.invoke(new DisplayStringRequest(servant, text));
    }
}
