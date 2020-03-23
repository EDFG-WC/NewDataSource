package com.laowang.datasource.javaconcurrency.phase2.chapter19;

public class TrueResult implements Result {
    private final Object resultValue;

    public TrueResult(Object resultValue) {
        this.resultValue = resultValue;
    }

    @Override public Object getResultValue() {
        return resultValue;
    }
}
