package com.laowang.datasource.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleStream {

  public static void main(String[] args) {
    //have a dish list (menu)

    List<Dish> menu = Arrays.asList(
        new Dish("pork", false, 800, Dish.Type.MEAT),
        new Dish("beef", false, 700, Dish.Type.MEAT),
        new Dish("chicken", false, 400, Dish.Type.MEAT),
        new Dish("french fries", true, 530, Dish.Type.OTHER),
        new Dish("rice", true, 350, Dish.Type.OTHER),
        new Dish("season fruit", true, 120, Dish.Type.OTHER),
        new Dish("pizza", true, 550, Dish.Type.OTHER),
        new Dish("prawns", false, 300, Dish.Type.FISH),
        new Dish("salmon", false, 450, Dish.Type.FISH));

    List<String> collect = getDishNameByStream(menu);
//    System.out.println(collect);
//
//    List<String> dishNameByCollections = getDishNameByCollections(menu);
//    System.out.println(dishNameByCollections);

//    Stream<Dish> stream = menu.stream();
//    stream.forEach(System.out::print);
    System.out.println("==============");
    List<String> result = menu.parallelStream().filter(dish -> {
      System.out.println("filter->" + dish.getName());
      return dish.getCalories() > 300;
    }).map(dish -> {
      System.out.println("map->" + dish.getName());
      return dish.getName();
    }).collect(Collectors.toList());
    System.out.println("==============");
    System.out.println(result);
  }

  private static List<String> getDishNameByCollections(List<Dish> menu) {
    List<Dish> lowCalories = new ArrayList<>();
    for (Dish d : menu) {
      if (d.getCalories() < 400) {
        lowCalories.add(d);
      }
    }
    lowCalories.sort((d1, d2) -> d1.getCalories() - d2.getCalories());
    List<String> dishNameList = new ArrayList<>();
    for (Dish d : lowCalories) {
      dishNameList.add(d.getName());
    }
    return dishNameList;
  }

  private static List<String> getDishNameByStream(List<Dish> menu) {
    return menu.stream().filter(dish -> dish.getCalories() < 400)
        .sorted((d1, d2) -> d1.getCalories() - d2.getCalories()).map(dish -> dish.getName())
        .collect(
            Collectors.toList());
  }

  // 厉害的多线程处理: 用了forkjoin-thread, 可以更快
  private static List<String> getDishNameByParalleStream(List<Dish> menu) {
    return menu.parallelStream().filter(dish -> dish.getCalories() < 400)
        .sorted((d1, d2) -> d1.getCalories() - d2.getCalories()).map(dish -> dish.getName())
        .collect(
            Collectors.toList());
  }
}
