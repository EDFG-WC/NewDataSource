package com.laowang.datasource.javaconcurrency.msb.juc.c_020;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class T07half_TestCyclicBarrier {
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(N);
        for (int i = 0; i < 8; i++)
            new Writer(barrier).start();
    }

    static class Writer extends Thread {
        private CyclicBarrier cyclicBarrier;
        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("執行緒" + Thread.currentThread().getName() + "正在寫入資料...");
            try {
                Thread.sleep(5000); // 以睡眠來模擬寫入資料操作
                System.out.println("執行緒" + Thread.currentThread().getName() + "寫入資料完畢，等待其他執行緒寫入完畢");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("所有執行緒寫入完畢，繼續處理其他任務...");
        }
    }
}
