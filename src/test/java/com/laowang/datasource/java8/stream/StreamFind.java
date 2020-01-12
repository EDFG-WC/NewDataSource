package com.laowang.datasource.java8.stream;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamFind {

  public static void main(String[] args) {
    Stream<Integer> stream1 = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
    Optional<Integer> optional1 = stream1.filter(integer -> integer % 2 == 0).findAny();
    System.out.println(optional1.get());
    Stream<Integer> stream2 = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
    Optional<Integer> optional2 = stream2.filter(integer -> integer > 100).findAny();
    // find方法和和optional.orElse是完全一样的...
    System.out.println(optional2.orElse(-1));
    int i = find(new Integer[]{1, 2, 3, 4, 5, 6, 7}, -1, integer -> integer > 100);
    System.out.println(i);
    Stream<Integer> stream3 = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
    Optional<Integer> optional3 = stream3.filter(integer -> integer % 2 == 0).findFirst();
    optional3.ifPresent(System.out::println);
    // optional还可以接一个filter操作.
    optional2.filter(integer -> integer == 2).get();
  }

  private static int find(Integer[] values, int defaultValue, Predicate<Integer> predicate) {
    for (int value : values) {
      if (predicate.test(value)) {
        return value;
      }
    }
    return defaultValue;
  }
}
