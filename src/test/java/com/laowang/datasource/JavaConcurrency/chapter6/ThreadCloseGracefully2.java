package com.laowang.datasource.JavaConcurrency.chapter6;

public class ThreadCloseGracefully2 {
    private static class Worker extends Thread {

        @Override
        public void run() {
             while (true) {
                 try {
                     Thread.sleep(1);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                     break;
                 }
             }
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
