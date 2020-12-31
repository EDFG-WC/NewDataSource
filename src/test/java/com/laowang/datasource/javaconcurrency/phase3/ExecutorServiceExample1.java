package com.laowang.datasource.javaconcurrency.phase3;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * 用线程池执行多线程任务出错怎么办?
 *
 * 调用了shutdown方法的线程池还能再添加任务吗? 答案是no.
 */
public class ExecutorServiceExample1 {
    public static void main(String[] args) throws InterruptedException {
        // isShutDown();
        // isTerminated();
        // executeRunnableError();
        executeRunnableTask();
    }

    private static void isShutDown() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(service.isShutdown());
        service.shutdown();
        System.out.println(service.isShutdown());
        // 问题来了: 当我执行shutdown之后, 还能执行一个callable吗
        service.execute(() -> {
            System.out.println("能看到就说明shutdown()之后还能添加任务 ");
        });
    }

    private static void isTerminated() {
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.shutdown();
        System.out.println(service.isShutdown());
        System.out.println(service.isTerminated());
        System.out.println(((ThreadPoolExecutor)service).isTerminating());
    }

    /**
     * 此时我们看到, 异常被跑出, 主程序执行到底.
     *
     * @throws InterruptedException
     */
    private static void executeRunnableError() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10, new MyThreadFactory());
        service.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        IntStream.range(0, 10).boxed().forEach(i -> service.execute(() -> System.out.println(1 / 0)));
        service.shutdown();
        service.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("==========================");
    }

    private static void executeRunnableTask() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10, new MyThreadFactory());
        IntStream.range(0, 10).boxed().forEach(i -> service.execute(new MyTask(i) {
            @Override
            protected void error(Throwable cause) {
                System.out.println("No." + i + " thread went wrong.");
            }

            @Override
            protected void done() {
                System.out.println("Successful no: " + i);
            }

            @Override
            protected void doExecute() {
                if (i % 3 == 0) {
                    int tmp = i / 0;
                }
            }

            @Override
            protected void doInit() {
                // 啥 都不干
            }
        }));
        service.shutdown();
        service.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("==========================");
    }

    /** 这个类就是之前把线程设为守护的那个参数: 可以用来处理异常 */
    private static class MyThreadFactory implements ThreadFactory {
        private static final AtomicInteger SEQ = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("My-Thread-" + SEQ.getAndIncrement());
            thread.setUncaughtExceptionHandler((t, cause) -> {
                System.out.println("Thread: " + t.getName() + " went wrong.");
                cause.printStackTrace();
                System.out.println("=======================================");
            });
            return thread;
        }
    }

    /**
     * 一个模板类, 可以帮助我们在出现异常的时候准确捕捉异常, 可以覆盖ThreadFactory的功能
     */
    private abstract static class MyTask implements Runnable {
        protected final int no;

        public MyTask(int no) {
            this.no = no;
        }

        @Override
        public void run() {
            try {
                this.doInit();
                this.doExecute();
                this.done();
            } catch (Throwable cause) {
                this.error(cause);
            }
        }

        protected abstract void error(Throwable cause);

        protected abstract void done();

        protected abstract void doExecute();

        protected abstract void doInit();
    }
}
