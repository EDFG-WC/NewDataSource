package com.laowang.datasource.javaconcurrency.phase2.chapter19;

/**
 * 表示实际的执行结果的类
 */
public class RealResult implements Result {
    private final Object resultValue;

    public RealResult(Object resultValue) {
        this.resultValue = resultValue;
    }

    @Override public Object getResultValue() {
        return resultValue;
    }
}
