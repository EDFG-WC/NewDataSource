/**
 * 认识Executor
 */
package com.laowang.datasource.javaconcurrency.msb.juc.c_026_01_ThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

public class T01_MyExecutor implements Executor {

    public static void main(String[] args) {
        new T01_MyExecutor().execute(() -> System.out.println("hello executor"));
    }

    @Override
    public void execute(Runnable command) {
        // new Thread(command).run();
        System.out.println("heyhey");
        command.run();

    }

}
