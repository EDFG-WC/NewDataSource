package com.laowang.datasource.javaconcurrency.phase1.chapter6;

public class ThreadService {
    // 传进来任务的执行线程
    private Thread executeThread;

    private boolean isFinished = false;

    /**
     * 调起runner线程, 主线程阻塞.
     * 
     * @param task
     */
    public void execute(Runnable task) {
        executeThread = new Thread() {
            @Override
            public void run() {
                Thread runner = new Thread(task);
                // 设置为守护线程之后, runner会随着主线程关闭而关闭
                runner.setDaemon(true);
                runner.start();
                runner.setName("executeThread");
                try {
                    // 主线程执行runner.join() 主线程阻塞, runner不结束不会走完.
                    runner.join();
                    // 结束之后会把flag设置为true.
                    isFinished = true;
                } catch (InterruptedException e) {
                    System.out.println("executeThread.interrupt()执行了");
                }
            }
        };
        executeThread.start();
    }

    /**
     * 让任务多少毫秒之内暴力结束
     * 
     * @param mills
     */
    public void shutdown(long mills) {
        long currentTime = System.currentTimeMillis();
        // 如果没有执行本方法, 线程执行结束之后, 线程自动退出也不会往下走.
        while (!isFinished) {
            if ((System.currentTimeMillis() - currentTime) >= mills) {
                System.out.println("任务已经超时, 立即结束");
                executeThread.interrupt();
                break;
            }
            try {
                // 如果没执行结束, 也没有超时, 就短暂地休眠一下:
                executeThread.sleep(1);
            } catch (InterruptedException e) {
                // 如果能运行到这里, 说明是主线程调用了interrupt()方法
                System.out.println("执行线程被打断!");
                break;
            }
        }
    }
}
