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
                    //runner阻塞, 不结束不会走完. 结束之后会把flag设置为true.
                    runner.join();
                    isFinished = true;
                } catch (InterruptedException e) {
                }
            }
        };
        executeThread.start();
    }
    public void shuntdown(long mills) {
        long currentTime = System.currentTimeMillis();
        //如果没有执行结束, 如果执行结束之后, 线程自动退出也不会往下走.
        while (!isFinished) {
            if ((System.currentTimeMillis()-currentTime)>=mills) {
                System.out.println("任务已经超时, 立即结束");
                executeThread.interrupt();
                break;
            }
            try {
                //如果没执行结束, 也没有超时, 就短暂地休眠一下:
                executeThread.sleep(1);
            } catch (InterruptedException e) {
                //如果能运行到这里, 说明是主线程调用了interrupt()方法
                System.out.println("执行线程被打断!");
                break;
            }
        }
    }
}
