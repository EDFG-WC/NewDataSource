package com.laowang.datasource.JavaConcurrency.chapter13;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimpleThreadPool {
    /**
     * 线程池线程数
     */
    private final int size;

    /**
     * 默认线程数
     */
    private final static int DEFAULT_SIZE = 10;

    private static volatile int seq = 0;

    /**
     * 线程名的前缀
     */
    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL";

    /**
     * 线程组
     */
    private final static ThreadGroup GROUP = new ThreadGroup("POOL_GROUP");


    /**
     * 存放runnable类对象的list, 相当于是一个任务的队列
     */
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    /**
     * 内部类WorkerTask的list
     */
    private final static List<WorkerTask> THREAD_QUEUE = new ArrayList<>();

    public SimpleThreadPool() {
        // 这样就可以调到类的有参构造(perfect)
        this(DEFAULT_SIZE);
    }

    public SimpleThreadPool(int size) {
        this.size = size;
        init();
    }

    /**
     * 初始化的方法
     */
    private void init() {
        //size是线程的数量 以循环的方式来实现多线程的效果
        for (int index = 0; index < size; index++) {
            createWorkTask();
        }
    }


    private void createWorkTask() {
        WorkerTask task = new WorkerTask(GROUP, THREAD_PREFIX + (seq++));
        task.start();
        THREAD_QUEUE.add(task);
    }

    private enum TaskState {
        FREE, RUNNING, BLOCKED, DEAD;
    }

    public void submit(Runnable runnable) {
        synchronized (TASK_QUEUE) {
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    private static class WorkerTask extends Thread {
        private volatile TaskState state = TaskState.FREE;

        public WorkerTask(ThreadGroup group, String name) {
            super(group, name);
        }

        public TaskState getTaskState() {
            return this.state;
        }

        public void run() {
            OUTER:
            while (this.state != TaskState.DEAD) {
                Runnable runnable;
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            state = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            break OUTER;
                        }
                    }
                }
                runnable = TASK_QUEUE.removeFirst();
                if (runnable != null) {
                    state = TaskState.RUNNING;
                    runnable.run();
                    state = TaskState.FREE;
                }
            }
        }

        public void close() {
            this.state = TaskState.DEAD;
        }
    }

    public static void main(String[] args) {
        SimpleThreadPool threadPool = new SimpleThreadPool();
        for (int i = 0; i < 40; i++) {
            threadPool.submit(() -> {
                System.out.println("The runnable is serviced by " + Thread.currentThread().getName() + " starts.");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The runnable is serviced by " + Thread.currentThread().getName() + " finished.");
            });
        }
    }
}