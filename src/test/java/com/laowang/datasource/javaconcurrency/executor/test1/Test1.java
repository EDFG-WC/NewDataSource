package com.laowang.datasource.javaconcurrency.executor.test1;

import java.util.concurrent.*;

public class Test1 {
  private static final int CORE_POOL_SIZE = 5;
  private static final int MAX_POOL_SIZE = 10;
  private static final int QUEUE_CAPACITY = 100;
  private static final Long KEEP_ALIVE_TIME = 1L;

  public static void main(String[] args) {
    ThreadPoolExecutor executor =
        new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(QUEUE_CAPACITY),
            new ThreadPoolExecutor.CallerRunsPolicy());
    for (int index = 0; index < 10; index++) {
      CountTask countTask = new CountTask();
      executor.execute(countTask);
    }
    executor.shutdown();
    while (!executor.isTerminated()) {}
    System.out.println("Finished all threads");
  }
}
