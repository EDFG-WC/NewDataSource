package com.laowang.datasource.java8.stream;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

// reduce是一个terminal的操作, 根据给定的function给stream一个操作
public class StreamReduce {

  public static void main(String[] args) {
    Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
    // 用reduce取和, 0是初始值
    Integer reduce = stream.reduce(0, ((integer, jnteger) -> integer + jnteger));
    System.out.println(reduce);

    stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
    stream.reduce((i,j)->i+j).ifPresent(System.out::println);

    //用reduce取最大最小值, 这都是
    stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
    stream.reduce((i,j)-> i>j?i:j).ifPresent(System.out::println);
    stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
    stream.reduce(Integer::min).ifPresent(System.out::println);

    //可以做乘积
    stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
    Integer result = stream.filter(integer -> integer % 2 == 0).reduce(1, (i, j) -> i * j);
    System.out.println(result);

    Optional.of(result).ifPresent(System.out::println);
  }
}
