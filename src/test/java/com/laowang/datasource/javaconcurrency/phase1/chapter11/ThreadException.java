package com.laowang.datasource.javaconcurrency.phase1.chapter11;

public class ThreadException {
    private final static int A = 10;
    private final static int B = 0;

    public static void main(String[] args) {
        /*Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(5_000L);
                int result = A / B;
                System.out.println(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread1");
        thread1.setUncaughtExceptionHandler((thread,e)->{
            System.out.println(e);
            System.out.println(thread);
        });
        thread1.start();*/
        new Test1().test();
    }
}
