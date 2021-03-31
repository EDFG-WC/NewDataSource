package com.laowang.datasource.javaconcurrency.phase3;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class ExecutorExample2 {
    public static void main(String[] args) throws InterruptedException {
        stealingPool();
    }

    private static void stealingPool() throws InterruptedException {
        /**
         * 号称可以尽可能地调用CPU的多核资源, 它可以自动结束.
         * 这个线程池用的是ForkJoinPool, 它的核心线程数是电脑可用核心数(Runtime.getRuntime().availableProcessors())
         * ForkJoinPool.defaultForkJoinWorkerThreadFactory,
         */
        // ExecutorService service = Executors.newWorkStealingPool();
        // Optional.of(Runtime.getRuntime().availableProcessors()).ifPresent(System.out::println);
        ExecutorService service = Executors.newWorkStealingPool();
        List<Callable<String>> list = IntStream.range(0, 20).boxed().map(i -> (Callable<String>)() -> {
            System.out.println("Thread " + Thread.currentThread().getName());
            sleep(2);
            return "task-" + i;
        }).collect(toList());
        // invokeAll: 把任务放进线程池执行的方法, 返回List<Future>. 一般获取future的方法都不是阻塞方法, 但invokeAll是阻塞方法. future只是任务返回的一个票据, 我们要根据票据来获取值:
        List<Future<String>> futures = service.invokeAll(list);
        System.out.println("非阻塞测试器");
        // 只有在获取future的时候才会阻塞:
        futures.stream().map(future -> {
            try {
                return future.get();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }).forEach(System.out::println);
        System.out.println("阻塞测试器==============");
    }

    // 把会遇到异常的部分抽出来:
    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
