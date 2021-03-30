package com.laowang.datasource.javaconcurrency.phase1.chapter6;

import java.util.concurrent.TimeUnit;

/**
 * 捕获异常版
 * interrupt方法执行之后, 一定要有一个捕获InterruptedException的方法准备运行
 */
public class ThreadCloseGracefully2 {
    private static class Worker extends Thread {

        @Override
        public void run() {
             while (true) {
                 // 模拟一个耗时的操作
                 // 打断的核心1: 必须有个要捕获InterruptedException的方法执行
                 try {
                     Thread.sleep(1);
                 } catch (InterruptedException e) {
                     System.out.println("线程内打断");
                     // 打断的核心2: 代码能够正常执行完
                     break;
                 }
             }
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            System.out.println("线程外打断");
        }
        worker.interrupt();
    }
}
