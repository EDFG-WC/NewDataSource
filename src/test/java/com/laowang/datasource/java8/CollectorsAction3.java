package com.laowang.datasource.java8;

// 如果对象是静态的, 那么就可以用这样的方式引入.

import static com.laowang.datasource.java8.CollectorsAction.menu;

import com.laowang.datasource.java8.stream.Dish;
import com.laowang.datasource.java8.stream.Dish.Type;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;


public class CollectorsAction3 {

  public static void main(String[] args) {
    testGroupByConcurrentWithFunction();
    testGroupByConcurrentWithFunctionAndCollector();
    testGroupByConcurrentWithFunctionAndSupplierAndCollector();
    testJoining();
    testJoiningWithDelimiter();
    testJoiningWithDelimiterAndPrefixAndSuffix();
    testMapping();
    testMaxBy();
    testMinBy();
  }

  // groupingByConcurrent的效果是拿到一个ConcurrentMap的类的对象
  private static void testGroupByConcurrentWithFunction() {
    System.out.println("testGroupByConcurrent");
    // 类的加载有2种, 一种是主动加载, 一种是被动加载.
    // 这种模式下, 如果menu是被final修饰的, 那么它就是被动加载, 不到用的时候不会被加载
    ConcurrentMap<Type, List<Dish>> collect = menu.stream()
        .collect(Collectors.groupingByConcurrent(Dish::getType));
    Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);
    Optional.ofNullable(collect).ifPresent(System.out::println);
  }

  private static void testGroupByConcurrentWithFunctionAndCollector() {
    System.out.println("testGroupByConcurrentWithFunctionAndCollector");
    // groupingByConcurrent的第二个参数, 是一个collectors, 指定了返回的内容, 可以不是menu的全部
    ConcurrentMap<Type, Double> collect = menu.stream().collect(
        Collectors.groupingByConcurrent(Dish::getType, Collectors.averagingInt(Dish::getCalories)));
    Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);
    Optional.ofNullable(collect).ifPresent(System.out::println);
  }

  // ConcurrentSkipListMap跟redis一样, 它主要的数据结构是跳表, 执行速度非常快(用空间换时间).
  private static void testGroupByConcurrentWithFunctionAndSupplierAndCollector() {
    System.out.println("testGroupByConcurrentWithFunctionAndSupplierAndCollector");
    ConcurrentSkipListMap<Type, Double> collect = menu.stream()
        .collect(Collectors.groupingByConcurrent(Dish::getType, ConcurrentSkipListMap::new,
            Collectors.averagingInt(Dish::getCalories)));
    Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);
    Optional.ofNullable(collect).ifPresent(System.out::println);
  }

  // 把流里的某个东西获取到并且连在一起然后返回一个string
  private static void testJoining() {
    System.out.println("testJoining");
    String collect = menu.stream().map(Dish::getName).collect(Collectors.joining());
    Optional.ofNullable(collect).ifPresent(System.out::println);
  }

  private static void testJoiningWithDelimiter() {
    System.out.println("testJoiningWithDelimiter");
    String collect = menu.stream().map(Dish::getName).collect(Collectors.joining(","));
    Optional.ofNullable(collect).ifPresent(System.out::println);
  }

  private static void testJoiningWithDelimiterAndPrefixAndSuffix() {
    System.out.println("testJoiningWithDelimiterAndPrefixAndSuffix");
    Optional.of(menu.stream().map(Dish::getName).collect(Collectors.joining(",", "Names[", "]")))
        .ifPresent(System.out::println);
  }

  //joining方法是不能直接用的, 要在这之前调用一次map方法, Collectors.mapping就替代了这个步骤
  private static void testMapping() {
    System.out.println("testMapping");
    Optional.of(menu.stream().collect(Collectors.mapping(Dish::getName, Collectors.joining(","))))
        .ifPresent(System.out::println);
  }

  private static void testMaxBy() {
    System.out.println("testMaxBy");
    menu.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)))
        .ifPresent(System.out::println);
  }

  private static void testMinBy() {
    System.out.println("testMinBy");
    menu.stream().collect(Collectors.minBy(Comparator.comparingInt(Dish::getCalories)))
        .ifPresent(System.out::println);
  }
}
