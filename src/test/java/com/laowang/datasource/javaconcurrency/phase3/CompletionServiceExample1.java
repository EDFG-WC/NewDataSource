package com.laowang.datasource.javaconcurrency.phase3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/26 QQ交流群:601980517，463962286 Future的缺点: 没有callback, 最好不要让我自己去取, 最好你自己返回给我.
 ***************************************/
public class CompletionServiceExample1 {

    /**
     * Demo the Future defect.
     *
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        futureDefect1();
         futureDefect2();
    }

    /**
     * Future缺点: No callback(最好就是你给我返回值), get()方法还是阻塞.
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void futureDefect1() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> future = service.submit(() -> {

            try {
                TimeUnit.SECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 100;
        });
        System.out.println("=================");
        future.get();
    }

    /*
     * Future缺点2: 如果我放一组任务到线程池, 我得到的返回一定是根据任务时长进行排序的list, 而且所有任务都会阻塞.
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void futureDefect2() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(2);
        final List<Callable<Integer>> callableList = Arrays.asList(() -> {
            sleep(10);
            System.out.println("The 10 finished");
            return 10;
        }, () -> {
            sleep(20);
            System.out.println("The 20 finished");
            return 20;
        });

        List<Future<Integer>> futures = new ArrayList<>();
        futures.add(service.submit(callableList.get(1)));
        futures.add(service.submit(callableList.get(0)));

        for (Future<Integer> future : futures) {
            System.out.println(future.get());
        }
    }

    /**
     * sleep the specify seconds
     *
     * @param seconds
     */
    private static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}