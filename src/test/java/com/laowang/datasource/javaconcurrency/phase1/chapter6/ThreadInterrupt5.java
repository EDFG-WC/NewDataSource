package com.laowang.datasource.javaconcurrency.phase1.chapter6;

public class ThreadInterrupt5 {
    static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    if (MyThread.interrupted()) {
                        System.out.println("已经是停止状态了，我要退出了！");
                        throw new InterruptedException();
                    }
                }

            } catch (InterruptedException e) {
                System.out.println("在MyThread类中的run方法中被捕获");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            MyThread myThread = new MyThread();
            myThread.start();
            Thread.sleep(2000);
            System.out.println("状态：" + MyThread.interrupted());
            myThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
