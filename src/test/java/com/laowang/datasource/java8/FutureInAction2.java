package com.laowang.datasource.java8;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// JDK官方Future.
public class FutureInAction2 {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    // 单线程的线程池
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Callable<String> stringCallable = () -> {
      try {
        Thread.sleep(10000);
        return "I'm finished.";
      } catch (InterruptedException e) {
        return "error";
      }
    };
    Future<String> future = executorService.submit(stringCallable);
    // 这里可以做自己的逻辑了

    while (!future.isDone()) {
      Thread.sleep(10);
    }
    // 这个get方法不会在代码没有执行结束之后返回null, 而是会等待线程跑完, 得到结果.
    String value = future.get();
    System.out.println(value);
    // 线程池用完要shutdown.
    executorService.shutdown();
  }
}
