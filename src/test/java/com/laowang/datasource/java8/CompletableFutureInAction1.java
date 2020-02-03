package com.laowang.datasource.java8;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureInAction1 {

  private static final Random RANDOM = new Random(System.currentTimeMillis());

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    CompletableFuture<Double> completableFuture = new CompletableFuture<>();
    new Thread(() -> {
      double value = get();
      completableFuture.complete(value);
    }).start();
    System.out.println("=======no block=======");
    Optional.ofNullable(completableFuture.get()).ifPresent(System.out::println);

    // 异步的一个方法,BiConsumer的参数一个是返回值,一个是异常(throwable)
    completableFuture.whenComplete((v, t) -> {
      Optional.ofNullable(v).ifPresent(System.out::println);
      Optional.ofNullable(t).ifPresent(x -> x.printStackTrace());

    });
  }

  static double get() {
    try {
      Thread.sleep(RANDOM.nextInt(10_000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return RANDOM.nextDouble();
  }
}
