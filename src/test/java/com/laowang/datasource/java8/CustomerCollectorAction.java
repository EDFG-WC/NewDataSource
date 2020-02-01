package com.laowang.datasource.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CustomerCollectorAction {

  public static void main(String[] args) {

    // java 8自带的toList方法
    List<String> result = Arrays
        .asList("alex", "wang", "hello", "lambda", "collector", "java 8", "stream").parallelStream()
        .filter(s -> s.length() >= 5).collect(Collectors.toList());
    System.out.println(result);

    // 自己实现的toList类
    Collector<String, List<String>, List<String>> collector = new ToListCollector();
    String[] arr = new String[]{"alex", "wang", "hello", "lambda", "collector", "java 8", "stream"};
    List<String> collect = Arrays.stream(arr).filter(s -> s.length() >= 5).collect(collector);
    System.out.println(collect);

    // 自己实现的toSet类
    Collector<String, Set<String>, Set<String>> toSetCollector = new ToSetCollector();
    Set<String> res = Arrays
        .asList("alex", "wang", "hello", "lambda", "collector", "java 8", "stream").stream()
        .filter(s -> s.length() >= 5).collect(toSetCollector);
    System.out.println(res);

    // 实际上, ToSetCollector和ToListCollector几乎没有区别. 仅仅是实现的泛型不一样. 前者是Collector<T, Set<T>, Set<T>>, 后者是Collector<T, List<T>, List<T>>

    // 在使用的时候, A可以直接写成?(也许是因为它是一个中间值的原因?), 但是R必须和实现类一样
    Collector<String, ?, Set<String>> aSetCollector = new ToSetCollector();
    Set<String> list = Arrays
        .asList("alex", "wang", "hello", "lambda", "collector", "java 8", "stream").stream()
        .filter(s -> s.length() >= 5).collect(aSetCollector);
    System.out.println(list);

    // 也可以不在创建ToSetCollector对象的时候指明泛型. 这样就能根据默认的实现返回泛型.
    Set<String> collect1 = Arrays
        .asList("alex", "wang", "hello", "lambda", "collector", "java 8", "stream").stream()
        .filter(s -> s.length() >= 5).collect(new ToSetCollector<>());
    System.out.println(collect1);

    List<String> collect2 = Arrays
        .asList("alex", "wang", "hello", "lambda", "collector", "java 8", "stream").stream()
        .filter(s -> s.length() >= 5).collect(new ToListCollector<>());
    System.out.println(collect2);
  }
}
