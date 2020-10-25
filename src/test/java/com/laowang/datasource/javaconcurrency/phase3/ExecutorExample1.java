package com.laowang.datasource.javaconcurrency.phase3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ExecutorExample1 {
    public static void main(String[] args) throws InterruptedException {
        // useCachedThreadPool();
        // useFixedSizePool();
        useSinglePool();
    }

    /**
     * These pools will typically improve the performance of programs that execute many short-lived asynchronous tasks.
     *
     * 0,
     * 
     * Integer.MAX_VALUE,
     * 
     * 60L, TimeUnit.SECONDS,
     * 
     * new SynchronousQueue<Runnable>() ---> 这个队列每次插入内容之前, 必须保证之前的内容被移除(就是说队列的size只有1)
     */
    private static void useCachedThreadPool() throws InterruptedException {
        // 基于我们对于SynchronousQueue的认识, 它的队列只能放一个任务, 而她也没给maxSize做限制, 这个时候, 你塞多少任务给它, 它就会创建多少线程. 所以才会说只适合短生命周期的任务
        // 而且有一个比较重要的点就是它coreSize为0, 所以keepAliveTime一过就立即销毁所有线程, 程序会自动停止
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(((ThreadPoolExecutor)service).getActiveCount());
        service.execute(() -> System.out.println("==================="));
        System.out.println(((ThreadPoolExecutor)service).getActiveCount());
        IntStream.range(0, 100).boxed().forEach(i -> {
            service.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " [" + i + "]");
            });
        });
        TimeUnit.SECONDS.sleep(1);
        // foreach那里直接提交了100个任务给线程池
        System.out.println(((ThreadPoolExecutor)service).getActiveCount());
    }

    /**
     * Fixed代表着: coreSize和maxSize一样大, 超过nThreads这个量也不会有新的线程创建了
     * 
     * nThreads,
     * 
     * nThreads,
     * 
     * 0L, TimeUnit.MILLISECONDS,
     * 
     * new LinkedBlockingQueue<Runnable> // 超过nThread的任务会存储在这个最大数量是integer上限的queue里面
     * 
     */
    private static void useFixedSizePool() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        System.out.println(((ThreadPoolExecutor)service).getActiveCount());
        service.execute(() -> System.out.println("==================="));
        System.out.println(((ThreadPoolExecutor)service).getActiveCount());
        IntStream.range(0, 100).boxed().forEach(i -> {
            service.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " [" + i + "]");
            });
        });
        TimeUnit.SECONDS.sleep(1);
        // foreach那里直接提交了100个任务给线程池
        System.out.println(((ThreadPoolExecutor)service).getActiveCount());
        service.shutdown();
    }

    /**
     * 问题: 它和我单独启动一个线程有什么区别?
     * 
     * 1. newSingleThreadExecutor不shutdown不会死
     * 
     * 2. newSingleThreadExecutor可以把任务存进队列, 而单独启动一个线程不行
     * 
     * 1,
     * 
     * 1,
     * 
     * 0L, TimeUnit.MILLISECONDS,
     * 
     * new LinkedBlockingQueue<Runnable>() // 也是可以塞很多任务进去
     *
     * FinalizableDelegatedExecutorService 是真正的返回对象, 是ExecutorService的实现, ExecutorService的api很少, 增强了简洁性
     */
    private static void useSinglePool() throws InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        // System.out.println(((ThreadPoolExecutor)service).getActiveCount());
        service.execute(() -> System.out.println("==================="));
        // System.out.println(((ThreadPoolExecutor)service).getActiveCount());
        IntStream.range(0, 100).boxed().forEach(i -> {
            service.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " [" + i + "]");
            });
        });
        TimeUnit.SECONDS.sleep(1);
        // foreach那里直接提交了100个任务给线程池
        // System.out.println(((ThreadPoolExecutor)service).getActiveCount());
    }
}
