package com.laowang.datasource.javaconcurrency.phase2.chapter8;

/**
 * 真正做事的接口
 *
 * @param <T>
 */
public interface FutureTask<T> {

  T call();
}
