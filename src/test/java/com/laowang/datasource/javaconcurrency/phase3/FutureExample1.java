package com.laowang.datasource.javaconcurrency.phase3;

import java.util.concurrent.*;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/26 QQ交流群:601980517，463962286
 ***************************************/
public class FutureExample1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        // testGet();
        testGetWithTimeOut();
    }

    /**
     * {@link Future#get()} Who?
     */
    private static void testGet() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        // ================================
        System.out.println("=====I will be printed immediately.=====");
        // ================================
        // 假设我们要打断线程池的方法, 应该找调用future.get()方法的线程:
        Thread callerThread = Thread.currentThread();
        // 然后我们另起一个线程来执行打断:
        new Thread(() -> {
            try {
                // 以防打断的速度过快.
                TimeUnit.MILLISECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            callerThread.interrupt();
        }).start();
        // 这样也可以打断:
        // TimeUnit.MILLISECONDS.sleep(3);
        // callerThread.interrupt();
        // get会陷入阻塞
        Integer result = future.get();
        System.out.println("==============" + result + "==============");
    }

    /**
     * question: 超时之后, get()会立即返回异常, 但任务会继续执行, 假设这个任务已经发到另外一个微服务里去了怎么停止? 答案是get的时候给一个uuid, 如果超时了, 再调接口把其他微服务对应uuid的任务干掉
     * dist cp
     *
     * yarn applicationJobId
     *
     * process
     *
     * kill -9 process yarn -kill applicationJobId
     * <p>
     * 5 hours
     *
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws TimeoutException
     */
    private static void testGetWithTimeOut() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("===============");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        // 既然get会陷入阻塞, 那么就一定会有超时相关方法和参数, 但是! 超时之后会立即返回并且报异常, 但任务会继续执行.
        Integer result = future.get(5, TimeUnit.SECONDS);
        System.out.println(result);
    }
}
