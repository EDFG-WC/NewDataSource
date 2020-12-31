package com.laowang.datasource.javaconcurrency.phase3;

import java.util.concurrent.*;

/***************************************
 * @author:Alex Wang
 * @Date:2017/8/26 QQ交流群:601980517，463962286
 ***************************************/
public class CompletionServiceExample2 {
    /**
     * CompletionService的实现目标是任务先完成可优先获取到, 即结果按照完成先后顺序排序
     * @param args
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        /*ExecutorService service = Executors.newFixedThreadPool(2);
        final List<Callable<Integer>> callableList = Arrays.asList(() -> {
            sleep(10);
            System.out.println("The 10 finished");
            return 10;
        }, () -> {
            sleep(20);
            System.out.println("The 20 finished");
            return 20;
        });
        // 用ExecutorCompletionService把service包一下
        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(service);
        
        List<Future<Integer>> futures = new ArrayList<>();
        callableList.stream().forEach(callable -> futures.add(completionService.submit(callable)));
        
        Future<Integer> future;*/
        // take会先拿到优先返回的值.
        /*while ((future = completionService.take()) != null) {
            System.out.println(future.get());
        }*/

        // poll不是一个阻塞方法, 如果没有对等待时间进行设置, 会直接返回null
        /*Future<Integer> future = completionService.poll();
        System.out.println(future);*/

        // System.out.println(completionService.poll(11, TimeUnit.SECONDS).get());

        ExecutorService service = Executors.newFixedThreadPool(2);
        ExecutorCompletionService<Event> completionService = new ExecutorCompletionService<>(service);
        final Event event1 = new Event(1);
        final Event event2 = new Event(2);
        completionService.submit(new MyTask(event1), event1);
        completionService.submit(new MyTask(event2), event2);
        // take返回的是任务队列的第一个返回值. 如果不停打印, 就可以打印到后面任务的返回, take是阻塞方法.
        System.out.println(completionService.take().get().result);
        // System.out.println(completionService.take().get().result);
    }

    private static class MyTask implements Runnable {

        private final Event event;

        private MyTask(Event event) {
            this.event = event;
        }

        @Override
        public void run() {
            sleep(10);
            event.setResult("CURRENT THREAD " + event.getEventId() + " RUN SUCCESSFULLY.");
        }
    }

    private static class Event {
        final private int eventId;
        private String result;

        private Event(int eventId) {
            this.eventId = eventId;
        }

        public int getEventId() {
            return eventId;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
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
