package com.laowang.datasource.JavaConcurrency.chapter13;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SimpleThreadPool extends Thread {
    /**
     * 线程池线程数
     */
    private int size;

    /**
     * 当前任务数
     */
    private final int queueSize;

    /**
     * 默认线程数
     */
    private final static int DEFAULT_TASK_QUEUE_SIZE = 10;

    private final static int DEFAULT_MIN_SIZE = 4;

    private final static int DEFAULT_ACTIVE_SIZE = 8;

    private final static int DEFAULT_MAX_SIZE = 12;

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

    private final DiscardPolicy discardPolicy;

    private final static DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException("Discard this task.");
    };


    private volatile boolean destory = false;

    // 最小线程数
    private int min;

    // 最大线程数
    private int max;

    // 正常线程数
    private int active;

    public SimpleThreadPool() {
        // 这样就可以调到类的有参构造(perfect)
        this(DEFAULT_MIN_SIZE, DEFAULT_ACTIVE_SIZE, DEFAULT_MAX_SIZE, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    public SimpleThreadPool(int min, int active, int max, int queueSize, DiscardPolicy discardPolicy) {
        this.min = min;
        this.active = active;
        this.max = max;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    /**
     * 初始化的方法
     */
    private void init() {
        //size是线程的数量 以循环的方式来实现多线程的效果
        // 自动扩充线程池版本一开始要用最小值来启动线程:
        for (int index = 0; index < this.min; index++) {
            createWorkTask();
        }
        this.size = min;
        this.start();
    }

    /**
     * 创建任务
     */
    private void createWorkTask() {
        WorkerTask task = new WorkerTask(GROUP, THREAD_PREFIX + (seq++));
        task.start();
        THREAD_QUEUE.add(task);
    }

    @Override
    public void run() {
        while (!destory) {
            System.out.printf("Pool#Min:%d, Active:%d, Max: %d, Current: %d, QueueSize:%d\n", this.min, this.active, this.max, this.size, TASK_QUEUE.size());
            try {
                Thread.sleep(5_000L);
                // 假设task_queuesize>active>size, 就增加active-size个线程: (因为min==size, 所以就是加了4个)
                if (TASK_QUEUE.size() > active && size < active) {
                    for (int i = size; i < active; i++) {
                        createWorkTask();
                    }
                    System.out.println("The pool has increased to active.");
                    size = active;
                } else if (TASK_QUEUE.size() > max && size < max) {
                    for (int i = size; i < max; i++) {
                        createWorkTask();
                    }
                    System.out.println("The pool has increased to max.");
                    size = max;
                }
                // 任务队列空了之后, 把WorkerTask的对象关闭, 打断, 把线程池的size降低:
                if (TASK_QUEUE.isEmpty() && size > active) {
                    System.out.println("=============Reduce=============");
                    synchronized (TASK_QUEUE) {
                        int releaseSize = size - active;
                        for (Iterator<WorkerTask> it = THREAD_QUEUE.iterator(); it.hasNext(); ) {
                            if (releaseSize <= 0) {
                                break;
                            }
                            WorkerTask next = it.next();
                            next.close();
                            next.interrupt();
                            it.remove();
                            releaseSize--;
                        }
                        size =active;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 用来终止线程池运行的方法:
     *
     * @throws InterruptedException
     */
    public void shutdown() throws InterruptedException {
        while (!TASK_QUEUE.isEmpty()) {
            Thread.sleep(50);
        }
        int initVal = THREAD_QUEUE.size();
        while (initVal > 0) {
            for (WorkerTask task : THREAD_QUEUE) {
                if (task.getTaskState() == TaskState.BLOCKED) {
                    task.interrupt();
                    //把任务状态设置为死亡
                    task.close();
                    initVal--;
                } else {
                    Thread.sleep(10);
                }
            }
        }
        this.destory = true;
        System.out.println("The thread pool disposed.");
    }

    public int getSize() {
        return size;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public boolean isDestory() {
        return this.destory;
    }


    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getActive() {
        return active;
    }

    private enum TaskState {
        FREE, RUNNING, BLOCKED, DEAD;
    }

    /**
     * submit方法: 先把要执行的runnable对象添加进TASK_QUEUE, 在run方法那边取出来执行; 接着在执行结束之后唤醒其他线程;
     *
     * @param runnable
     */
    public void submit(Runnable runnable) {
        if (destory) {
            throw new IllegalStateException("The thread pool already destory and not allow submit any task.");
        }
        synchronized (TASK_QUEUE) {
            // 拒绝策略: 任务队列的大小超过queueSize的时候, 就抛出异常.
           /* if (TASK_QUEUE.size() > queueSize) {
                discardPolicy.discard();
            }*/
            // 把runnable对象添加到队列的最后面
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }

    public static class DiscardException extends RuntimeException {
        /**
         * Constructs a new runtime exception with {@code null} as its
         * detail message.  The cause is not initialized, and may subsequently be
         * initialized by a call to {@link #initCause}.
         */
        public DiscardException(String message) {
            super(message);
        }
    }

    public interface DiscardPolicy {
        public void discard() throws DiscardException;
    }

    /**
     * 自定义工作类
     */
    private static class WorkerTask extends Thread {
        /**
         * 工作类初始状态: FREE
         */
        private volatile TaskState state = TaskState.FREE;

        /**
         * 构造方法
         *
         * @param group
         * @param name
         */
        public WorkerTask(ThreadGroup group, String name) {
            super(group, name);
        }

        /**
         * 获取任务状态
         *
         * @return
         */
        public TaskState getTaskState() {
            return this.state;
        }

        /**
         * 重写run方法
         */
        public void run() {
            // OUTER: 设定的循环退出的位置
            OUTER:
            while (this.state != TaskState.DEAD) {
                Runnable runnable;
                synchronized (TASK_QUEUE) {
                    // 如果为空, 就一直等待, while-true的效果就是当判断条件为true的时候, 后面的代码永远不会被执行.
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            state = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            System.out.println("Task closed.");
                            break OUTER;
                        }
                    }
                }
                // 把队列里的第一个取出来;
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

    public static void main(String[] args) throws InterruptedException {
        //使用拒绝策略: 40个要执行的线程, 但queueSize只有10 于是就抛异常了
        //SimpleThreadPool threadPool = new SimpleThreadPool(6, 10, SimpleThreadPool.DEFAULT_DISCARD_POLICY);
        SimpleThreadPool threadPool = new SimpleThreadPool();
        for (int i = 0; i < 100; i++) {
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
//        Thread.sleep(10_000);
        // threadPool.shutdown();
//        threadPool.submit(() -> System.out.println("==============="));
    }
}