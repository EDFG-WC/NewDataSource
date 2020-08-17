package com.laowang.datasource.javaconcurrency.executor.test2;

import java.util.concurrent.Callable;

public class StringTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(1000L);
        return Thread.currentThread().getName();
    }
}
