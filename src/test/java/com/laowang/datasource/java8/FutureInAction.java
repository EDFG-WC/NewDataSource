package com.laowang.datasource.java8;


import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class FutureInAction {

  public static void main(String[] args) throws InterruptedException {
    Future<String> future = invoke(() -> {
      try {
        Thread.sleep(10000);
        return "I'm finished.";
      } catch (InterruptedException e) {
        return "error";
      }
    });
    // 这里只会返回null
    System.out.println(future.get());
    // 这里我们的主线程就可以空闲出来做其他事情了.
    // 等待future返回完成
    while (!future.isDone()) {
      Thread.sleep(10);
    }
    System.out.println(future.get());
    // 老办法, 主线程会被阻塞在这里, 什么都做不了
    String value = block(() -> {
      try {
        Thread.sleep(10000);
        return "I'm finished.";
      } catch (InterruptedException e) {
        return "error";
      }
    });
    System.out.println(value);
  }

  // 老办法
  private static <T> T block(Callable<T> callable) {
    return callable.action();
  }

  private static <T> Future<T> invoke(Callable<T> callable) {
    AtomicReference<T> result = new AtomicReference<>();
    AtomicBoolean isFinished = new AtomicBoolean(false);
    Thread t = new Thread(() -> {
      T value = callable.action();
      result.set(value);
      isFinished.set(true);
    });
    t.start();
    Future<T> future = new Future<T>() {
      @Override
      public T get() {
        return result.get();
      }

      @Override
      public boolean isDone() {
        return isFinished.get();
      }
    };
    return future;
  }

  private interface Future<T> {

    T get();

    boolean isDone();
  }

  private interface Callable<T> {

    T action();
  }
}
