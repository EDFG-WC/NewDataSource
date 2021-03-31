package com.laowang.datasource.javaconcurrency.phase3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 演示拒绝策略:
 */
public class ExecutorServiceExample2 {
    public static void main(String[] args) throws InterruptedException {
        // ThreadPoolExecutor executor = testAbort();
        // ThreadPoolExecutor executor = testDiscard();
        ThreadPoolExecutor executor = testCallerRun();
        // 等待1s, 确定不发生指令重排, 让这3个任务进入线程池
        TimeUnit.SECONDS.sleep(1);

        /*for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 额外做事");
            });
        }*/
    }

    /**
     * 超过maxSize+ queueSize的线程会直接被抛弃, 然后直接报异常, 已经提交的正常执行
     * 
     * @return executor
     */
    private static ThreadPoolExecutor testAbort() {
        ThreadPoolExecutor executor =
            new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), r -> {
                Thread t = new Thread(r);
                return t;
            }, new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 3; i++) {
            executor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        return executor;
    }

    /**
     * 超过max+queueSize的直接拒绝 没有任何提示
     * 
     * @return executor
     */
    private static ThreadPoolExecutor testDiscard() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1),
            Thread::new, new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 3; i++) {
            executor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        return executor;
    }

    /**
     * 超出的任务会由调用execute的线程来执行
     * 
     * @return executor
     */
    private static ThreadPoolExecutor testCallerRun() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(8),
            Thread::new, new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " 出来做事");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        return executor;
    }
}
