package com.laowang.datasource.javaconcurrency.msb.juc.c_020;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class T07_TestCyclicBarrier {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        // CyclicBarrier barrier = new CyclicBarrier(20);

        CyclicBarrier barrier = new CyclicBarrier(20, () -> System.out.println("满人, 发车"));

        for (int i = 0; i < 20; i++) {
      new Thread(
              () -> {
                try {
                  barrier.await();
                  System.out.println("正常工作");
                } catch (InterruptedException | BrokenBarrierException e) {
                  e.printStackTrace();
                }
              })
          .start();
        }
        // testCyclicBarrier();
    }

    public static void testCyclicBarrier() throws BrokenBarrierException, InterruptedException {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            System.out.println(Thread.currentThread().getName() + " 执行barrierAction");
        });
        new Thread(() -> {

            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " 到达屏障");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + " 执行业务");

            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println(Thread.currentThread().getName() + " 到达屏障");
        cyclicBarrier.await();
        System.out.println("屏障结束");
    }
}
