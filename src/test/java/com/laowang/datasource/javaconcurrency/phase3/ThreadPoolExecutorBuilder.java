package com.laowang.datasource.javaconcurrency.phase3;

import java.util.concurrent.*;

/** 如何使用线程池执行器 */
public class ThreadPoolExecutorBuilder {
    public static void main(String[] args) {
        // ExecutorService的方法不够丰富, 我们要观察线程池状况要强制转换成ThreadPoolExecutor类
        ThreadPoolExecutor executorService = (ThreadPoolExecutor)buildThreadPoolExecutor();
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
     * 7大参数: int corePoolSize, // 核心线程数, 即使线程池空闲, 也会在线程池中待命
     * 
     * int maximumPoolSize, // 最大线程数,
     *
     * long keepAliveTime, // 如果某个非核心线程空闲, 那么超过这个时间, 线程就会被回收
     *
     * TimeUnit unit, // 空闲时间的单位
     *
     * BlockingQueue<Runnable> workQueue, // 超过最大线程数的任务会先进队列等待.
     *
     * ThreadFactory threadFactory, // 其实可以缺省, 自定义可以搞一些事
     *
     * RejectedExecutionHandler handle //拒绝策略: 处理核心线程和阻塞队列都无法容纳的任务的方法
     * new ThreadPoolExecutor.AbortPolicy()有明显的问题: 当加入的任务超过队列数量会直接抛异常, 已经添加的任务执行完之后 线程池不再可用
     * new ThreadPoolExecutor.CallerRunsPolicy()则不会
     * 
     * 在这个方法中我们测试最大线程数2和阻塞队列容量1的情况下, 第4个任务被添加进线程池会发生什么 答案是直接抛RejectedExecutionException
     */
    private static ExecutorService buildThreadPoolExecutor() {
        ExecutorService executor = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1),
            Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        System.out.println("线程池创建完毕, 开始添加任务.");
        executor.execute(() -> sleepSeconds(2));
        executor.execute(() -> sleepSeconds(2));
        executor.execute(() -> sleepSeconds(2));
        // 添加第4个任务 因为我们的设计 这个任务会被拒绝策略接管
        // executor.execute(() -> sleepSeconds(2));
        return executor;
    }

    private static void sleepSeconds(long seconds) {
        try {
            System.out.println("* " + Thread.currentThread().getName() + " starts to work. *");
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
