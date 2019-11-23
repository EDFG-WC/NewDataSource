package com.laowang.datasource.javaconcurrency.phase1.chapter6;

public class ThreadCloseGracefully {
    private static class Worker extends Thread {
        private volatile boolean start = true;

        @Override
        public void run() {
            while (start) {

            }
        }

        public void shutdown() {
            this.start = false;
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
        worker.shutdown();
    }
}
