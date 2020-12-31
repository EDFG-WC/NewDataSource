package com.laowang.datasource.javaconcurrency.phase3;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * 用ExecutorCompletionService包裹一次ExecutorService的对象, 然后再把任务扔进池子 它的优化之处在于, 在这n个任务中, 谁先执行完谁先返回
 */
public class CompletionServiceTest1 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(service);
        /*IntStream.range(0, 5).boxed().forEach(i -> {
        completionService.submit(new ReturnAfterSleepCallable(i));
        });*/
        // 这里倒着遍历, sleep 5s的先进去, 但先出来的是执行最快的.
        for (int i = 5; i > 0; i--) {
            completionService.submit(new ReturnAfterSleepCallable(i));
        }
        System.out.println("after submit");
        IntStream.range(0, 5).boxed().forEach(i -> {
            try {
                System.out.println("result: " + completionService.take().get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        System.out.println("after get");
        service.shutdown();
    }

    private static class ReturnAfterSleepCallable implements Callable<String> {
        private int sleep;

        public ReturnAfterSleepCallable(int sleep) {
            this.sleep = sleep;
        }

        @Override
        public String call() throws Exception {
            TimeUnit.SECONDS.sleep(sleep);
            return System.currentTimeMillis() + ",sleep=" + String.valueOf(sleep);
        }
    }
}
