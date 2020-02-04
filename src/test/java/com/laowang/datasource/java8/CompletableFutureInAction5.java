package com.laowang.datasource.java8;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CompletableFutureInAction5 {

  public static void main(String[] args) throws InterruptedException {
//    // runAfterBoth(): 是在2个异步工作都结束之后, 执行本身的第二个参数(一个runnable对象)
//    Runnable runnable = () -> System.out.println("done");
//    // AtomicReference类是专门用来把lambda表达式里的值存起来的方法.
//    AtomicReference<Double> value = new AtomicReference<Double>(0d);
//    CompletableFuture.supplyAsync(() -> {
//      System.out.println(Thread.currentThread().getName() + " is running.");
//      try {
//        Thread.sleep(100);
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//      double theValue = CompletableFutureInAction1.get();
//      value.set(theValue);
//      return theValue;
//    }).runAfterBoth(CompletableFuture.supplyAsync(() -> {
//      System.out.println(Thread.currentThread().getName() + " is running.");
//      return 2;
//    }), runnable);
//
//    // 阻塞掉主线程, 然后让异步线程完成设置value值的工作.
//    Thread.currentThread().join();
//    while (value.get() != 0d) {
//      System.out.println(value.get());
//    }
//
//    // applyToEither()方法的效果是2边哪个先执行完就把哪个值给到第二个参数里进行下一步的执行, applyToEither()的第二个参数是一个function
//    CompletableFuture.supplyAsync(() -> {
//      System.out.println("I'm future 1");
//      return CompletableFutureInAction1.get();
//    }).applyToEither(CompletableFuture.supplyAsync(() -> {
//      System.out.println("I'm future 2");
//      return CompletableFutureInAction1.get();
//    }), v -> v * 10)
//        .thenAccept(System.out::println);
//    // 跟runAfterBoth()一样, 结果还没有打印出来, 主线程就会结束.
//    Thread.currentThread().join();
//
//    // acceptEither()方法的效果是2边哪个先执行完就把哪个值给到第二个参数里进行下一步的执行, 它的第二个参数是一个consumer
//    CompletableFuture.supplyAsync(() -> {
//      System.out.println("I'm future 1");
//      return CompletableFutureInAction1.get();
//    }).acceptEither(CompletableFuture.supplyAsync(() -> {
//      System.out.println("I'm future 2");
//      return CompletableFutureInAction1.get();
//    }), v-> System.out.println(v*10));
//    // 跟runAfterBoth()一样, 结果还没有打印出来, 主线程就会结束.
//    Thread.currentThread().join();
//
//    // runAfterEither()方法的效果是2边哪个先执行完就把哪个值给到第二个参数里进行下一步的执行, 它的第二个参数是一个supplier
//    CompletableFuture.supplyAsync(() -> {
//      System.out.println("I'm future 1");
//      return CompletableFutureInAction1.get();
//    }).runAfterEither(CompletableFuture.supplyAsync(() -> {
//      System.out.println("I'm future 2");
//      return CompletableFutureInAction1.get();
//    }), ()-> System.out.println("done"));
//    // 跟runAfterBoth()一样, 结果还没有打印出来, 主线程就会结束.
//    Thread.currentThread().join();

    List<CompletableFuture<Double>> collect = Arrays.asList(1, 2, 3, 4)
        .stream()
        .map(num -> CompletableFuture
            .supplyAsync(CompletableFutureInAction1::get))
        .collect(Collectors.toList());
    // allof()要等待全部完成
    CompletableFuture
        .allOf(collect.toArray(new CompletableFuture[collect.size()]))
        .thenRun(() -> System.out.println("done"));
    // anyof()只要有一个完成就结束
    CompletableFuture
        .anyOf(collect.toArray(new CompletableFuture[collect.size()]))
        .thenRun(() -> System.out.println("done"));
    Thread.currentThread().join();
  }
}
