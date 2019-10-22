package com.laowang.datasource.JavaConcurrency.chapter6;

public class ThreadCloseGracefully3 {
    private static class Worker extends Thread {

        @Override
        public void run() {
            while (true) {
                if (currentThread().isInterrupted()) {
                    break;
                }
            }
            //break意味着循环之外的代码还能够运行, 而return就代表着后面的代码就不会再执行了.
            System.out.println("hahaha");
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.interrupt();
    }
}
