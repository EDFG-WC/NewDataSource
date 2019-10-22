package com.laowang.datasource.JavaConcurrency.chapter5;

public class ThreadJoin3 {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread thread1 = new Thread(new CaptureRunnable("M1", 10_000L));
        Thread thread2 = new Thread(new CaptureRunnable("M2", 30_000L));
        Thread thread3 = new Thread(new CaptureRunnable("M3", 15_000L));
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        long endTime = System.currentTimeMillis();
        System.out.printf("Save data begins in: %s, ends in: %s\n", startTime, endTime);
    }
}

class CaptureRunnable implements Runnable {

    private String machineName;

    private long timeCost;

    public CaptureRunnable(String machineName, long timeCost) {
        this.machineName = machineName;
        this.timeCost = timeCost;
    }

    @Override
    public void run() {
        //DO THE REAL BUSINESS.
        try {
            Thread.sleep(timeCost);
            System.out.println(machineName + " completed data capture successfully.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getResult() {
        return machineName + " finish.";
    }
}
