package com.laowang.datasource.javaconcurrency.phase2.chapter19;

/**
 * 表示请求的抽象类.
 */
public abstract class MethodRequest {
    protected final Servant servant;
    protected final FutureResult futureResult;

    public MethodRequest(Servant servant, FutureResult futureResult) {
        this.servant = servant;
        this.futureResult = futureResult;
    }

    public abstract void execute();
}
