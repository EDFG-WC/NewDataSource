package com.laowang.datasource.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollectorIntroduction {

  public static void main(String[] args) {
    List<Apple> list = Arrays.asList(new Apple("green", 150)
        , new Apple("yellow", 120)
        , new Apple("green", 170)
        , new Apple("green", 150)
        , new Apple("yellow", 120)
        , new Apple("green", 170)
        , new Apple("blue", 9981));
    List<Apple> greenApples = list.stream().filter(apple -> apple.getColor().equals("green"))
        .collect(Collectors.toList());
//    Optional.ofNullable(greenApples).ifPresent(System.out::println);
//    Optional.ofNullable(normalGroup(list)).ifPresent(System.out::println);
//    Optional.ofNullable(functionGroup(list)).ifPresent(System.out::println);
    Optional.ofNullable(collectorGroup(list)).ifPresent(System.out::println);
  }

  private static Map<String, List<Apple>> normalGroup(List<Apple> apples) {
    Map<String, List<Apple>> appleMap = new HashMap<>();
    for (Apple apple : apples) {
      List<Apple> list = appleMap.get(apple.getColor());
      if (null == list) {
        list = new ArrayList<>();
        appleMap.put(apple.getColor(), list);
      }
      list.add(apple);
    }
    return appleMap;
  }

  private static Map<String, List<Apple>> functionGroup(List<Apple> apples) {
    Map<String, List<Apple>> appleMap = new HashMap<>();
    apples.stream().forEach(apple -> {
      List<Apple> colorList = Optional.ofNullable(appleMap.get(apple.getColor())).orElseGet(() -> {
        List<Apple> list = new ArrayList<>();
        appleMap.put(apple.getColor(), list);
        return list;
      });
      colorList.add(apple);
    });

    return appleMap;
  }

  private static Map<String, List<Apple>> collectorGroup(List<Apple> apples) {
    return apples.stream().collect(Collectors.groupingBy(Apple::getColor));
  }
}
