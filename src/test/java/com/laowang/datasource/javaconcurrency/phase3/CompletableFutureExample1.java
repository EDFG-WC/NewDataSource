package com.laowang.datasource.javaconcurrency.phase3;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/***************************************
 * @author:Alex Wang
 * @Date:2017/9/2 QQ交流群:601980517，463962286
 ***************************************/
public class CompletableFutureExample1 {
    public static void main(String[] args) throws InterruptedException {

        /*ExecutorService executorService = Executors.newFixedThreadPool(10);
        
        // ================================
        Future<?> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // ================================
        // 手动阻塞:
        while (!future.isDone()) {
        
        }
        System.out.println("DONE");*/
        // 最理想的状况是, 我不主动调结果, 最好的结果是让线程池通知我:
        // Unit
        /*CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).whenComplete((v, t) -> System.out.println("DONE")); // v代表函数接口的返回值, t代表可能出现的异常.
        System.out.println("========i am not blocked=========");
        // CompletableFuture.runAsync创建的是守护线程, 必须让调用者等待, 才能正确执行代码
        Thread.currentThread().join();*/

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> tasks =
            IntStream.range(0, 10).boxed().map(i -> (Callable<Integer>)() -> get()).collect(toList());

        executorService.invokeAll(tasks).stream().map(future -> {
            try {
                return future.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).parallel().forEach(CompletableFutureExample1::display);

        IntStream.range(0, 10).boxed().forEach(i -> CompletableFuture.supplyAsync(CompletableFutureExample1::get)
            .thenAccept(CompletableFutureExample1::display).whenComplete((v, t) -> System.out.println(i + " DONE")));
        Thread.currentThread().join();
    }

    private static void display(int data) {
        int value = ThreadLocalRandom.current().nextInt(20);
        try {
            System.out.println(Thread.currentThread().getName() + "display will be sleep " + value);
            TimeUnit.SECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "display execute done " + data);
    }

    private static int get() {
        int value = ThreadLocalRandom.current().nextInt(20);
        try {
            System.out.println(Thread.currentThread().getName() + " get will be sleep " + value);
            TimeUnit.SECONDS.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "get execute done " + value);
        return value;
    }
}
