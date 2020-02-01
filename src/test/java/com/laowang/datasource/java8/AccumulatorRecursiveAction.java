package com.laowang.datasource.java8;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

// 这种的compute方法没有返回值
public class AccumulatorRecursiveAction extends RecursiveAction {

  private final int start;

  private final int end;

  private final int[] data;

  public AccumulatorRecursiveAction(int start, int end, int[] data) {
    this.start = start;
    this.end = end;
    this.data = data;
  }

  private final int LIMIT = 3;

  @Override
  protected void compute() {
    if ((end - start) <= LIMIT) {
      for (int i = start; i < end; i++) {
        AccumulatorHelper.accumulate(data[i]);
      }
    } else {
      // 中间变量
      int mid = (start + end) / 2;
      AccumulatorRecursiveAction left = new AccumulatorRecursiveAction(start,
          mid, data);
      AccumulatorRecursiveAction right = new AccumulatorRecursiveAction(mid,
          end, data);
      left.fork();
      right.fork();
      left.join();
      right.join();
    }
  }

  static class AccumulatorHelper {

    private static final AtomicInteger result = new AtomicInteger(0);

    public static void accumulate(int value){
      result.getAndAdd(value);
    }

    public static int getResult() {
      return result.get();
    }
  }
}
