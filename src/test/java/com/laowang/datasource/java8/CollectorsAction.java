package com.laowang.datasource.java8;

import com.laowang.datasource.java8.stream.Dish;
import com.laowang.datasource.java8.stream.Dish.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CollectorsAction {

  public static List<Dish> menu = Arrays.asList(
      new Dish("pork", false, 800, Dish.Type.MEAT),
      new Dish("beef", false, 700, Dish.Type.MEAT),
      new Dish("chicken", false, 400, Dish.Type.MEAT),
      new Dish("french fries", true, 530, Dish.Type.OTHER),
      new Dish("rice", true, 350, Dish.Type.OTHER),
      new Dish("season fruit", true, 120, Dish.Type.OTHER),
      new Dish("pizza", true, 550, Dish.Type.OTHER),
      new Dish("prawns", false, 300, Dish.Type.FISH),
      new Dish("salmon", false, 450, Dish.Type.FISH));
  public static void main(String[] args) {
    testAveragingDouble();
    testAveragingInt();
    testAveragingLong();
    testCollectionAndThen();
    testCounting();
    testGroupingByFunction();
    testGroupingByFunctionAndCollection();
    testGroupingByFunctionAndSupplierAndCollection();
    testSummarizingInt();
  }

  // 下面三种都是一样的: 求平均值
  private static void testAveragingDouble() {
    System.out.println("testAveragingDouble");
    //averagingDouble: 隐式转换, 从小的类型往大的类型转
    Optional.ofNullable(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories)))
        .ifPresent(System.out::println);
  }

  private static void testAveragingInt() {
    System.out.println("testAveragingInt");
    Optional.ofNullable(menu.stream().collect(Collectors.averagingInt(Dish::getCalories)))
        .ifPresent(System.out::println);
  }

  private static void testAveragingLong() {
    System.out.println("testAveragingLong");
    Optional.ofNullable(menu.stream().collect(Collectors.averagingLong(Dish::getCalories)))
        .ifPresent(System.out::println);
  }

  // 得到平均值之后, then把lambda表达式返回
  private static void testCollectionAndThen() {
    System.out.println("testCollectionAndThen");
    Optional.ofNullable(menu.stream().collect(Collectors
        .collectingAndThen(Collectors.averagingInt(Dish::getCalories),
            a -> "The average calories is->" + a))).ifPresent(System.out::println);

    List<Dish> collect = menu.stream().filter(dish -> dish.getType().equals(Type.MEAT))
        .collect(Collectors.toList());
    collect.add(new Dish("", false, 100, Type.OTHER));
    System.out.println(collect);

    // collect不应被修改: 所以用collectThen传第二个参数, 屏蔽掉add权限, 这个东西用处会比较大.
    List<Dish> finalCollect = menu.stream().filter(dish -> dish.getType().equals(Type.MEAT))
        .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    // finalCollect.add(new Dish("", false, 100, Type.OTHER));
    System.out.println(finalCollect);
  }

  private static void testCounting() {
    System.out.println("testCounting");
    Optional.of(menu.stream().collect(Collectors.counting())).ifPresent(System.out::println);
  }

  private static void testGroupingByFunction() {
    System.out.println("testGroupingByFunction");
    Optional.of(menu.stream().collect(Collectors.groupingBy(dish -> dish.getType())))
        .ifPresent(System.out::println);
  }

  // groupingby的第二个参数带来的效果:
  private static void testGroupingByFunctionAndCollection() {
    System.out.println("testGroupingByFunctionAndCollection");
    Optional.of(menu.stream()
        .collect(Collectors.groupingBy(Dish::getType, Collectors.counting())))
        .ifPresent(System.out::println);
    Optional.of(menu.stream()
        .collect(Collectors.groupingBy(Dish::getType, Collectors.averagingInt(Dish::getCalories))))
        .ifPresent(System.out::println);
  }

  // 探究一下collect返回的是什么?
  private static void testGroupingByFunctionAndSupplierAndCollection() {
    System.out.println("testGroupingByFunctionAndSupplierAndCollection");
    Map<Type, Long> map = menu.stream()
        .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
    System.out.println(map.getClass().getName());
    // treeMap 可以作为参数传进去
    Map<Type, Long> treeMap = menu.stream()
        .collect(Collectors.groupingBy(Dish::getType, TreeMap::new, Collectors.counting()));
    System.out.println(treeMap.getClass().getName());
    System.out.println(treeMap);
  }

  // summarizingInt做了非常详尽的统计
  private static void testSummarizingInt() {
    System.out.println("testSummarizingInt");
    IntSummaryStatistics statistics = menu.stream()
        .collect(Collectors.summarizingInt(Dish::getCalories));
    Optional.of(statistics).ifPresent(System.out::println);
  }
}
