package com.laowang.datasource.java8;

// 如果对象是静态的, 那么就可以用这样的方式引入.

import static com.laowang.datasource.java8.CollectorsAction.menu;

import com.laowang.datasource.java8.stream.Dish;
import java.util.Collections;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;


public class CollectorsAction4 {

  public static void main(String[] args) {
    testSummingDouble();
    testSummingLong();
    testSummingInt();
    testToCollection();
    testToConcurrentMap();
    testToConcurrentMapWithBinaryOperator();
    testToConcurrentMapWithBinaryOperatorAndSupplier();
    testToList();
    testToSet();
  }

  // summingDouble就是得到卡路里的总和
  private static void testSummingDouble() {
    System.out.println("testSummingDouble");
    Optional.of(menu.stream().collect(Collectors.summingDouble(Dish::getCalories)))
        .ifPresent(System.out::println);

    Optional.of(menu.stream().map(Dish::getCalories).mapToInt(Integer::intValue).sum())
        .ifPresent(System.out::println);
  }

  private static void testSummingLong() {
    System.out.println("testSummingLong");
    Optional.of(menu.stream().collect(Collectors.summingLong(Dish::getCalories)))
        .ifPresent(System.out::println);
  }

  private static void testSummingInt() {
    System.out.println("testSummingInt");
    Optional.of(menu.stream().collect(Collectors.summingInt(Dish::getCalories)))
        .ifPresent(System.out::println);
  }

  private static void testToCollection() {
    System.out.println("testToCollection");
    Optional.of(menu.stream().filter(d -> d.getCalories() > 600).collect(Collectors.toCollection(
        LinkedList::new)))
        .ifPresent(System.out::println);
  }

  // toConcurrentMap 构造了一个名字-卡路里的map
  private static void testToConcurrentMap() {
    System.out.println("testToConcurrentMap");
    Optional.of(menu.stream()
        .collect(Collectors.toConcurrentMap(Dish::getName, Dish::getCalories)))
        .ifPresent(v -> {
          System.out.println(v);
          System.out.println(v.getClass());
        });
  }

  /**
   * 想要的结果: 类型-该类型的数量 多一个参数,
   */
  private static void testToConcurrentMapWithBinaryOperatorAndSupplier() {
    System.out.println("testToConcurrentMapWithBinaryOperator");
    Optional.of(menu.stream()
        .collect(Collectors.toConcurrentMap(Dish::getType, v -> 1L, (a, b) -> a + b,
            ConcurrentSkipListMap::new)))
        .ifPresent(v -> {
          System.out.println(v);
          System.out.println(v.getClass());
        });
  }

  /**
   * 想要的结果: 类型-该类型的数量
   */
  private static void testToConcurrentMapWithBinaryOperator() {
    System.out.println("testToConcurrentMapWithBinaryOperator");
    Optional.of(menu.stream()
        .collect(Collectors.toConcurrentMap(Dish::getType, v -> 1L, (a, b) -> a + b)))
        .ifPresent(v -> {
          System.out.println(v);
          System.out.println(v.getClass());
        });
  }

  private static void testToList() {
    System.out.println("testToList");
    Optional.of(menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList()))
        .ifPresent(r -> {
          System.out.println(r.getClass());
          System.out.println(r);
        });
  }

  private static void testToSet() {
    Optional.of(menu.stream().filter(Dish::isVegetarian).collect(Collectors.toSet()))
        .ifPresent(r -> {
          System.out.println(r.getClass());
          System.out.println(r);
        });
  }

  private static void testToMap() {
    System.out.println("testToMap");
    Optional.of(menu.stream().collect(
        Collectors.collectingAndThen(
            Collectors.toMap(Dish::getName, Dish::getCalories),
            Collections::synchronizedMap))
    ).ifPresent(v -> {
      System.out.println(v);
      System.out.println(v.getClass());
    });

    Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
    for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
      Thread key = entry.getKey();
      StackTraceElement[] value = entry.getValue();

      if (key.getId() != Thread.currentThread().getId()) {
        continue;
      }
      System.out.println("==========" + key.getName());
      for (StackTraceElement ste : value) {
        if (ste.isNativeMethod()) {
          continue;
        }
        System.out.println(ste.getClassName());
        System.out.println("isNativeMethod>" + ste.isNativeMethod());
        System.out.println(ste.getMethodName());
        System.out.println(ste.getLineNumber());
        System.out.println(ste.getFileName());
      }
    }
  }

  /**
   * Type:Total
   */
  private static void testToMapWithBinaryOperator() {
    System.out.println("testToMapWithBinaryOperator");
    Optional.of(menu.stream()
        .collect(Collectors.toMap(Dish::getType, v -> 1L, (a, b) -> a + b)))
        .ifPresent(v -> {
          System.out.println(v);
          System.out.println(v.getClass());
        });
  }

  /**
   * Type:Total
   */
  private static void testToMapWithBinaryOperatorAndSupplier() {
    System.out.println("testToMapWithBinaryOperatorAndSupplier");
    Optional.of(menu.stream()
        .collect(Collectors.toMap(Dish::getType, v -> 1L, (a, b) -> a + b, Hashtable::new)))
        .ifPresent(v -> {
          System.out.println(v);
          System.out.println(v.getClass());
        });
  }
}
