package com.laowang.datasource.javaconcurrency.phase3;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * 用ExecutorCompletionService包裹一次ExecutorService的对象, 然后再把任务扔进池子 它的优化之处在于, 在这n个任务中, 谁先执行完谁先返回
 */
public class CompletionServiceTest2 {
    public static void main(String[] args) {
        try {
            ExecutorService executor = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(7),
                Thread::new, new ThreadPoolExecutor.DiscardPolicy());
            ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(executor);
            IntStream.range(0, 10).boxed().forEach(i -> {
                completionService.submit(() -> {
                    int sleepValue = i;
                    System.out.println("sleep = " + sleepValue + ", name: " + Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(sleepValue);
                    return "?ha?: " + sleepValue + ", " + Thread.currentThread().getName();
                });
            });
            for (int i = 0; i < 10; i++) {
                System.out.println(completionService.take().get());
            }
            executor.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
