package com.laowang.datasource.java8;

import com.laowang.datasource.java8.AccumulatorRecursiveAction.AccumulatorHelper;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolTest {
  private static int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

  public static void main(String[] args) {
    System.out.println(clac());
    AccumulatorRecursiveTask task = new AccumulatorRecursiveTask(0, data.length,
        data);
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    Integer result = forkJoinPool.invoke(task);
    System.out.println("AccumulatorRecursiveTask=> "+result);
//    AccumulatorRecursiveAction action = new AccumulatorRecursiveAction(0,
//        data.length, data);
//    forkJoinPool.invoke(action);
//    System.out.println("AccumulatorRecursiveAction=> "+AccumulatorHelper.getResult());
  }

  private static int clac() {
    int result = 0;
    for (int i = 0; i < data.length; i++) {
      result += data[i];
    }
    return result;
  }
}
