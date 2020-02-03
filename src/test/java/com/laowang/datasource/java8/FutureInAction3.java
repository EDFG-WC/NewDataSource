package com.laowang.datasource.java8;


import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

// 回调功能: 假设我
public class FutureInAction3 {

  private static <T> Future<T> invoke(Callable<T> callable) {
    AtomicReference<T> result = new AtomicReference<>();
    AtomicBoolean isFinished = new AtomicBoolean(false);
    // 把Future代码放到Thread前面去, 要在线程启动之前就把回调函数调起来
    Future<T> future = new Future<T>() {
      private Completable<T> completable;

      @Override
      public T get() {
        return result.get();
      }

      @Override
      public boolean isDone() {
        return isFinished.get();
      }

      @Override
      public void setCompletable(Completable<T> completable) {
        this.completable = completable;
      }

      @Override
      public Completable<T> getCompletable() {
        return completable;
      }
    };

    Thread t = new Thread(() -> {
      // 首先把异常抓住
      try {
        T value = callable.action();
        result.set(value);
        isFinished.set(true);
        // 处理回调事件
        if (future.getCompletable() != null) {
          future.getCompletable().complete(value);
        }
      } catch (Throwable cause) {
        // 处理回调事件
        if (future.getCompletable() != null) {
          future.getCompletable().exception(cause);
        }
      }
    });
    t.start();
    return future;
  }

  private interface Future<T> {

    T get();

    boolean isDone();

    // 增加回调事件
    void setCompletable(Completable<T> completable);

    Completable<T> getCompletable();
  }


  private interface Callable<T> {

    T action();
  }

  // 回调事件
  private interface Completable<T> {

    void complete(T t);

    void exception(Throwable cause);
  }

  public static void main(String[] args) {
    Future<String> future = invoke(() -> {
      try {
        Thread.sleep(10_000);
        return "Job Done.";
      } catch (InterruptedException e) {
        return "Error happened.";
      }
    });
    // 重写回调函数, 增强回调函数的功能.
    future.setCompletable(new Completable<String>() {
      @Override
      public void complete(String s) {
        System.out.println(s);
        System.out.println("you are a moron.");
      }

      @Override
      public void exception(Throwable cause) {
        System.out.println("error");
        cause.printStackTrace();
      }
    });
    System.out.println("自己的业务逻辑.");
    // 自己实现的方法里, 没有回调函数的情况下, 想要在future.get()不拿到null, 只能写while循环等.
    // 有了回调函数之后, future.get()直接调用还是会返回null, 但是我们可以从重写的回调函数里拿到返回值value.
    System.out.println(future.get());
  }
}
