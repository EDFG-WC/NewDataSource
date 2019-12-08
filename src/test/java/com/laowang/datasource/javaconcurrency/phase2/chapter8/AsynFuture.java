package com.laowang.datasource.javaconcurrency.phase2.chapter8;

/**
 * Future的具体实现
 *
 * @param <T>
 */
public class AsynFuture<T> implements Future<T> {

  private volatile boolean isDone = false;

  private T result;

  public void done(T result) {
    synchronized (this) {
      this.result = result;
      this.isDone = true;
      this.notifyAll();
    }
  }

  @Override
  public T get() throws InterruptedException {
    synchronized (this) {
      while (!isDone) {
        this.wait();
      }
    }
    return result;
  }
}
