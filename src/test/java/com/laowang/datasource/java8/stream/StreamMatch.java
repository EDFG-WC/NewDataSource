package com.laowang.datasource.java8.stream;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamMatch {

  public static void main(String[] args) {
    Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
    // match的一系列方法的入参都是predicate
//    boolean match = stream.allMatch(integer -> integer > 10);
//    boolean match = stream.anyMatch(integer -> integer > 6);
    boolean match = stream.noneMatch(integer -> integer < 0);
    System.out.println(match);
  }
}
