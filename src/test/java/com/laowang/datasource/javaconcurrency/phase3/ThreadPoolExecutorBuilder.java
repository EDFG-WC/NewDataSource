package com.laowang.datasource.javaconcurrency.phase3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/** 如何使用线程池执行器 */
public class ThreadPoolExecutorBuilder {
    public static void main(String[] args) {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor)buildThreadPoolExecutor(); // ExecutorService的方法不够丰富,
                                                                                            // 我们要观察线程池状况要强制转换成ThreadPoolExecutor类
        int activeCount = -1;
        int queueSize = -1;
        while (true) {
            if (executorService.getActiveCount() != activeCount && executorService.getQueue().size() != queueSize) {
                System.out.println(executorService.getActiveCount());
                System.out.println(executorService.getCorePoolSize());
                System.out.println(executorService.getQueue().size());
                System.out.println(executorService.getMaximumPoolSize());
                activeCount = executorService.getActiveCount();
                queueSize = executorService.getQueue().size();
                System.out.println("===============================================================================");
            }
        }
    }

    /**
     * // 我们要测试的是: 1. coreSize=1, maxSize=2, blockingQueue=1和ThreadPoolExecutor.AbortPolicy() 提交4个任务之后会发生什么? 答案是: 最大2,
     * 等待队列1, 能处理的任务就3个, 到了第四个会直接把第四个任务作为一场跑出
     *
     * int corePoolSize, // 核心线程数, 即使线程池空闲, 也会在线程池中待命
     *
     * int maximumPoolSize, // 最大线程数,
     *
     * long keepAliveTime, // 如果某个非核心线程空闲, 那么超过这个时间, 线程就会被回收
     *
     * TimeUnit unit, // 空闲时间的单位
     *
     * BlockingQueue<Runnable> workQueue, // 超过最大线程数的任务会先进队列等待.
     *
     * ThreadFactory threadFactory, // 暂时不知道干嘛的? 其实可以缺省
     *
     * RejectedExecutionHandler handle //拒绝策略
     */
    private static ExecutorService buildThreadPoolExecutor() {
        ExecutorService executor = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1),
            Thread::new, new ThreadPoolExecutor.AbortPolicy());
        System.out.println("The Thread Pool Executor done.");
        executor.execute(() -> sleepSeconds(100));
        executor.execute(() -> sleepSeconds(10));
        executor.execute(() -> sleepSeconds(10));
        executor.execute(() -> sleepSeconds(10));
        return executor;
    }

    private static void sleepSeconds(long seconds) {
        try {
            System.out.println("* " + Thread.currentThread().getName() + " *");
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
