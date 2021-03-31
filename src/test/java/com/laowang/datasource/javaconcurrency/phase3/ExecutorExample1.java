package com.laowang.datasource.javaconcurrency.phase3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ExecutorExample1 {
    public static void main(String[] args) throws InterruptedException {
        // useCachedThreadPool();
         useFixedSizePool();
//        useSinglePool();
    }

    /**
     * 可以显著提升执行时间不长的异步任务的性能
     * 这个线程池的核心线程数量为0, 一旦没有任务keepAliveTime时间一过, 所有线程都会被销毁
     * 而它的最大线程数是无限大, 所以也有OOM的可能
     * SynchronousQueue的队列中只能有1个任务, 如果后面的任务肯定就执行不了了
     */
    private static void useCachedThreadPool() throws InterruptedException {
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
     * 这个线程池的线程容量通过传参确定(nThreads). 核心线程数和最大线程数是一样的.
     * 如果提交了超过nThreads的数量, 就会被存到LinkedBlockingQueue里, 这个queue的容量是Integer的上限.
     * 所以如果有人不开眼往里放了非常多任务, 一定会OOM
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
     * 它的核心线程和最大线程数都是1 接收任务的也是LinkedBlockingQueue可以接收无限多任务
     * 问题: 它和我单独启动一个线程有什么区别?
     * 1. newSingleThreadExecutor不shutdown不会死
     * 2. newSingleThreadExecutor可以把任务存进队列, 而单独启动一个线程不行
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
