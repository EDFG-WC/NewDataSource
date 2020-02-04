package com.laowang.datasource.java8;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureInAction4 {

  public static void main(String[] args) {
    // supplyAsync: 调用多线程异步处理; thenApply拿到前面的结果再处理一次; whenComplete结束的时候再做一些事
    CompletableFuture.supplyAsync(() -> 1)
        .thenApply(integer -> Integer.sum(integer, 10))
        .whenComplete((v, t) -> System.out.println(v));
    // handle是用来处理潜在异常的方法, v是结果, t是异常; thenRun的参数是一个runnable, 总得来说就是在complete之后又做了一些事情
    CompletableFuture.supplyAsync(() -> 1)
        .handle((v, t) -> Integer.sum(v, 10))
        .whenComplete((v, t) -> System.out.println(v))
        .thenRun(System.out::println);
    // thenAccept是消费之前流的内容, 不做返回.
    CompletableFuture.supplyAsync(() -> 1)
        .thenApply(integer -> Integer.sum(integer, 9))
        .thenAccept(System.out::println);
    // 对应thenAccept, thenCompose是的参数是Function
    CompletableFuture.supplyAsync(() -> 12)
        .thenCompose(integer -> CompletableFuture.supplyAsync(() -> 10 * integer))
        .thenAccept(System.out::println);
    // thenCombine是把2个函数式推导的结果, 按照thenCombine的第二个参数的内容进行处理
    CompletableFuture.supplyAsync(() -> 1)
        .thenCombine(CompletableFuture.supplyAsync(() -> 2.0d), (r1, r2) -> r1 + r2)
        .thenAccept(System.out::println);
    // thenAcceptBoth同时把2个函数式的结果都在它第二个参数里进行消费
    CompletableFuture.supplyAsync(() -> 1)
        .thenAcceptBoth(CompletableFuture.supplyAsync(() -> 2.0d), (r1, r2) -> {
          System.out.println(r1);
          System.out.println(r2);
          System.out.println(r1 + r2);
        });
  }
}
