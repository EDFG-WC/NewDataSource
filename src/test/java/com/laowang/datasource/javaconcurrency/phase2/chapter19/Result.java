package com.laowang.datasource.javaconcurrency.phase2.chapter19;

/**
 * 存储结果的接口, 分支出FutureResult和TrueResult.
 */
public interface Result {
    Object getResultValue();
}
