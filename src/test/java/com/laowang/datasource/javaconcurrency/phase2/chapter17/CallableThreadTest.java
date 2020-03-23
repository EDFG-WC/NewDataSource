package com.laowang.datasource.javaconcurrency.phase2.chapter17;

import java.util.concurrent.*;

public class CallableThreadTest implements Callable<Integer> {
    private final static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableThreadTest ctt = new CallableThreadTest();
        //        FutureTask<Integer> ft = new FutureTask<>(ctt);
        Future<Integer> submit = executor.submit(ctt);
        Integer o = submit.get();
        System.out.println(o);
        //        for (int i = 0; i < 100; i++) {
        //            System.out.println(Thread.currentThread().getName() + " 的循环变量i的值" + i);
        //            if (i == 20) {
        //                new Thread(ft, "有返回值的线程").start();
        //            }
        //        }
        //        try {
        //            System.out.println("子线程的返回值：" + ft.get());
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        } catch (ExecutionException e) {
        //            e.printStackTrace();
        //        }

    }

    @Override public Integer call() throws Exception {
        int i = 0;
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        return i;
    }

}

