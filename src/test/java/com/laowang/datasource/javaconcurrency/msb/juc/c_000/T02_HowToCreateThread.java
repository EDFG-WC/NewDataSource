package com.laowang.datasource.javaconcurrency.msb.juc.c_000;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class T02_HowToCreateThread {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello MyThread!");
        }
    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello MyRun!");
        }
    }

    static class MyCall implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("Hello MyCall");
            return "success";
        }
    }

    public static void main(String[] args) {
        // 1. 继承Thread
        new MyThread().start();
        // 2. 实现Runnable
        new Thread(new MyRun()).start();
        // 3. lambda表达式
        new Thread(() -> {
            System.out.println("Hello Lambda!");
        }).start();
        // 4. Callable
        Thread thread = new Thread(new FutureTask<String>(new MyCall()));
        thread.start();
        // 5. 线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            System.out.println("Hello ThreadPool");
        });
    }

}

// 请你告诉我启动线程的三种方式 1：Thread 2: Runnable 3:Executors.newCachedThread 4: lambda表达式 5: 实现Callable
