package com.laowang.datasource.JavaConcurrency.chapter6;

public class ThreadService {
    private Thread executeThread;

    private boolean isFinished = false;

    public void execute(Runnable task) {
        executeThread = new Thread() {
            @Override
            public void run() {
                Thread runner = new Thread(task);
                runner.setDaemon(true);
                runner.start();
                try {
                    runner.join();
                    isFinished = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        executeThread.start();
    }
    public void shuntdown(long mills) {
        long currentTime = System.currentTimeMillis();
        while (!isFinished) {
            if ((System.currentTimeMillis()-currentTime)>=mills) {
                System.out.println("任务已经超时, 立即结束");
                executeThread.interrupt();
                break;
            }
            try {
                executeThread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断!");
                break;
            }
        }
    }
}
