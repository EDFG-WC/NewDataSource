package com.laowang.datasource.javaconcurrency.phase2.chapter8;

public interface Future<T> {

  /**
   * 返回一个Future对象, 通过这个方法得到真正的结果
   *
   * @return
   * @throws InterruptedException
   */
  T get() throws InterruptedException;
}
