package com.laowang.datasource.javaconcurrency.phase3;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/26 QQ交流群:601980517，463962286
 ***************************************/
public class FutureExample2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // testIsDone();
        // testCancel();
        testCancel2();
    }

    /**
     * Completion may be due to normal termination, an exception, or cancellation: done的可能性有3种: 完成, 遇到异常, 或者取消了.
     */
    private static void testIsDone() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Integer> future = executorService.submit(() -> {
            throw new RuntimeException();
        });
        try {
            Integer result = future.get();
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(" is done " + future.isDone());
        }
    }

    /**
     * try to cancel the task but it could be failed: 可以尝试着取消, 但不一定成功.
     * <ul>
     * <li>task is completed already. 失败情况1: 任务执行完了</li>
     * <li>has already been cancelled. 失败情况2: 之前已经被取消了</li>
     * <li>cancel之后, isDone返回true, isCancel返回true</li>
     * </ul>
     */
    private static void testCancel() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicBoolean running = new AtomicBoolean(true);
        Future<Integer> future = executorService.submit(() -> {
            /*try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            while (running.get()) {
            }
            return 10;
        });

        TimeUnit.MILLISECONDS.sleep(10);
        // cancel()的参数, 是否允许正在运行的任务被打断, 返回值代表着是否被成功取消.
        System.out.println(future.cancel(true));
        System.out.println(future.isDone());
        System.out.println(future.isCancelled());

    }

    /**
     * try to cancel maybe failed
     * <ul>
     * <li>task is completed already.</li>
     * <li>has already been cancelled.</li>
     * </ul>
     */
    private static void testCancel2() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicBoolean running = new AtomicBoolean(true);
        Future<Integer> future = executorService.submit(() -> {
            /*try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("=======================");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            /*            while (running.get()) {
            }*/
            // cancel方法无法停止线程, 要配合interrupted方法来停止任务(停止任务指的是, 你可以不让后续代码执行, 但任务不会死)
            while (!Thread.interrupted()) {
                // 如果线程没被打断, 我要干嘛干嘛干嘛:

            }
            System.out.println("1111111");
            return 10;
        });

        TimeUnit.MILLISECONDS.sleep(10);
        // cancel方法无法停止线程
        System.out.println(future.cancel(true));
        System.out.println(future.isDone());
        System.out.println(future.isCancelled());
        TimeUnit.MILLISECONDS.sleep(20);
        // cancel()执行之后不会拿到结果
        System.out.println(future.get());
    }
}
