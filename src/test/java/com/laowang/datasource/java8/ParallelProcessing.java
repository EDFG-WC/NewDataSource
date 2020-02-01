package com.laowang.datasource.java8;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelProcessing {

  public static void main(String[] args) {
    // 本电脑为8核CPU
    System.out.println(Runtime.getRuntime().availableProcessors());
    // 设置parallelStream的线程数的方法
//    System.setProperty("java.util.concurrent.common.parallelism","20");
    System.out.println(
        "The best performance=>" + measureSumPerformance(ParallelProcessing::normalAdd, 10_000_000)
            + " MS");
    System.out.println(
        "The best performance=>" + measureSumPerformance(ParallelProcessing::parallelStream3,
            10_000_000) + " MS");
  }

  // 测量方法
  private static long measureSumPerformance(Function<Long, Long> adder, long limit) {
    long fastest = Long.MAX_VALUE;
    for (int i = 0; i < 10; i++) {
      long startTime = System.currentTimeMillis();
      Long result = adder.apply(limit);
      long duration = System.currentTimeMillis() - startTime;
      System.out.println("The result of sum=>" + result);
      if (duration < fastest) {
        fastest = duration;
      }
    }
    return fastest;
  }

  // 串行.
  private static long iterateStream(long limit) {
    return Stream.iterate(1L, i -> i + 1).limit(limit).reduce(0L, Long::sum);
  }

  // 并行.
  private static long parallelStream(long limit) {
    return Stream.iterate(1L, i -> i + 1).parallel().
        limit(limit).reduce(0L, Long::sum);
  }

  // 并行+拆箱
  private static long parallelStream2(long limit) {
    return Stream.iterate(1L, i -> i + 1).mapToLong(Long::longValue).parallel().
        limit(limit).reduce(0L, Long::sum);
  }

  // LongStream+并行+拆箱: 这个相当快
  private static long parallelStream3(long limit) {
    return LongStream.rangeClosed(1, limit).parallel().reduce(0L, Long::sum);
  }

  // 普通方法只比parallelStream3慢
  private static long normalAdd(long limit) {
    long result = 0L;
    for (long i = 1L; i < limit; i++) {
      result += i;
    }
    return result;
  }
}
