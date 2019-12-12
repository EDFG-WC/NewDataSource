package com.laowang.datasource.javaconcurrency.phase2.chapter8;

import java.util.function.Consumer;

/**
 * 桥接Future和FutureTask, 做空的实现, 只是传递参数, 真正的业务是FutureTask的匿名内部类
 */
public class FutureService {

  public <T> Future<T> submit(final FutureTask<T> task) {
    AsynFuture<T> asynFuture = new AsynFuture<>();
    new Thread() {
      @Override
      public void run() {
        //等到那边线程执行完的时候, 这里才会调用done方法.
        T call = task.call();
        asynFuture.done(call);
      }
    }.start();
    return asynFuture;
  }

  public <T> Future<T> submit(final FutureTask<T> task, final Consumer<T> consumer) {
    AsynFuture<T> asynFuture = new AsynFuture<>();
    new Thread() {
      @Override
      public void run() {
        T result = task.call();
        asynFuture.done(result);
        consumer.accept(result);
      }
    }.start();
    return asynFuture;
  }
}
