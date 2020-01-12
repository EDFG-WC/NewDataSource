package com.laowang.datasource.java8.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFilter {

  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 6, 7, 7, 1);

    // filter 的参数是一个predicate, 满足就会被放进新的流.
    List<Integer> result1 = list.stream().filter(integer -> integer % 2 == 0)
        .collect(Collectors.toList());
    System.out.println(result1);
    List<Integer> result2 = list.stream().distinct().collect(Collectors.toList());
    System.out.println(result2);

    // skip是跳过若干个
    List<Integer> result3 = list.stream().skip(5).collect(Collectors.toList());
    System.out.println(result3);

    List<Integer> result4 = list.stream().limit(50).collect(Collectors.toList());
    System.out.println(result4);
  }
}
