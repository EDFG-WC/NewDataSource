package com.laowang.datasource.javaconcurrency.phase3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadPoolExecutorLongTimeTask {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor =
            new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), r -> {
                Thread t = new Thread(r);
                t.setDaemon(true);
                return t;
            }, new ThreadPoolExecutor.AbortPolicy());
        // 并行处理: 假设我们发送了一个请求到一个系统里, 那里有很多请求在排队
        IntStream.range(0, 10).boxed().forEach(i -> {
            executor.submit(() -> {
                // while (true) {
                // }
                try {
                    TimeUnit.SECONDS.sleep(6);
                    System.out.println(i + ": count, heihei");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        // 串行处理: 我们希望5s之后, 无论结果, 都要拿到一个返回, 但实际上shutdown, shutdownNow, awaitTermination三个方法都没有办法强行打断线程池的运行
        // 打断方案: 通过线程池的第六个参数把线程池里的所有线程都设置为守护线程, 让线程池的工作随着主线程结束而结束, 此时! 配合着awaitTermination()方法时间一到不管你完不完成我都结束
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        // executor.shutdownNow();
        System.out.println("串行化工作结束");
    }

}
