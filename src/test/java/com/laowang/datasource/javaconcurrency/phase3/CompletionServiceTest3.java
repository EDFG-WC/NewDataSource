package com.laowang.datasource.javaconcurrency.phase3;

import java.util.concurrent.*;

/**
 * 用ExecutorCompletionService包裹一次ExecutorService的对象, 然后再把任务扔进池子 它的优化之处在于, 在这n个任务中, 谁先执行完谁先返回
 */
public class CompletionServiceTest3 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService<String> service = new ExecutorCompletionService<String>(executorService);
        service.submit(() -> {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("3 seconds pass.");
            return "3秒";
        });
        System.out.println(service.poll());
        executorService.shutdown();
    }
}
