package com.laowang.datasource.java8;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class CompletableFutureInAction2 {

  public static void main(String[] args) throws InterruptedException {
//    AtomicBoolean isFinished = new AtomicBoolean(false);
//    CompletableFuture<Double> completableFuture = CompletableFuture
//        .supplyAsync(CompletableFutureInAction1::get);
//    completableFuture.whenComplete((v, t) -> {
//      Optional.ofNullable(v).ifPresent(System.out::println);
//      Optional.ofNullable(t).ifPresent(x -> x.printStackTrace());
//      isFinished.set(true);
//    });
//    System.out.println("=======no block=======");
//    while (!isFinished.get()) {
//      Thread.sleep(1);
//    }

    // 用线程池演进:
    // 这样是不会退出的, 因为这个线程不是守护线程. 只能用executorService.shutdown();停止
//    ExecutorService executorService = Executors.newFixedThreadPool(2);
    //
    ExecutorService executorService = Executors.newFixedThreadPool(2, r -> {
      Thread t = new Thread(r);
      t.setDaemon(true);
      return t;
    });
    executorService.execute(() -> System.out.println("test..."));

    // 二合一
    ExecutorService executor = Executors.newFixedThreadPool(2, r -> {
      Thread t = new Thread(r);
      t.setDaemon(true);
      return t;
    });
    AtomicBoolean isFinished = new AtomicBoolean(false);
    CompletableFuture<Double> completableFuture = CompletableFuture
        .supplyAsync(CompletableFutureInAction1::get, executor);
    completableFuture.whenComplete((v, t) -> {
      Optional.ofNullable(v).ifPresent(System.out::println);
      Optional.ofNullable(t).ifPresent(x -> x.printStackTrace());
      isFinished.set(true);
    });
    System.out.println("=======no block=======");
    while (!isFinished.get()) {
      Thread.sleep(1);
    }
  }
}
