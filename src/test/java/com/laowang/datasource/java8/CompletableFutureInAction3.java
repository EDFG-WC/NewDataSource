package com.laowang.datasource.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CompletableFutureInAction3 {

  public static void main(String[] args) {
    ExecutorService executor = Executors.newFixedThreadPool(2, r -> {
      Thread thread = new Thread(r);
      thread.setDaemon(false);
      return thread;
    });
    CompletableFuture.supplyAsync(CompletableFutureInAction1::get, executor)
        .thenApply(CompletableFutureInAction3::multiply)
        .whenComplete((v, t) -> Optional.
            ofNullable(v).
            ifPresent(System.out::println));

    List<Integer> productIDs = Arrays.asList(1, 2, 3, 4, 5);
    // 整合 completableFuture这个东西用起来就可以不怎么考虑多线程的问题了.
    List<Double> collect = productIDs
        .stream()
        .map(id -> CompletableFuture.supplyAsync(() -> queryProduct(id), executor))
        .map(future -> future.thenApply(CompletableFutureInAction3::multiply))
        .map(CompletableFuture::join)
        .collect(Collectors.toList());
    System.out.println(collect);
  }

  private static double multiply(double value) {
    try {
      Thread.sleep(1_000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return value * 10;
  }

  // 模拟查询, 实际就是休眠若干秒
  private static double queryProduct(int id) {
    return CompletableFutureInAction1.get();
  }
}
