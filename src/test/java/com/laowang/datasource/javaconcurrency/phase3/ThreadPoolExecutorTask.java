package com.laowang.datasource.javaconcurrency.phase3;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadPoolExecutorTask {
    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
            Thread::new, new ThreadPoolExecutor.AbortPolicy());
        IntStream.range(0, 20).boxed().forEach(i -> {
            executor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(20);
                    System.out.println(Thread.currentThread().getName() + "  [" + i + "] done.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        // 是一个非阻塞的方法, 会立即返回, 所以shutdown会立即执行.
        // executor.execute();
        // shutdown是一个非阻塞方法, 它的作用是: 1. 工作线程继续工作 2.打断所有空闲线程 3.结束掉所有线程
        // executor.shutdown();
        // awaitTermination是一个阻塞方法, 参数代表所阻塞的时间, 如果线程池任务执行完毕阻塞结束, 或者超过阻塞时间阻塞也会结束
        // executor.awaitTermination(3, TimeUnit.SECONDS);
        List<Runnable> runnableList = null;
        try {
            // shutdownNow不是一个阻塞方法 1. 把队列里所有任务直接返回 2. 打断所有线程. ps: 用这个方法就代表所有多线程的任务都放弃了
            runnableList = executor.shutdownNow();
            System.out.println("======over======");
            System.out.println(runnableList);
            System.out.println(runnableList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("======done======");
    }
}
