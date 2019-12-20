package com.laowang.datasource.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterApple {

  // 过滤的handler模式
  public static interface AppleFilter {

    boolean filter(Apple apple);
  }

  public static class GreenAndHigherThan160Filter implements AppleFilter {

    @Override
    public boolean filter(Apple apple) {
      if (apple.getColor().equals("green") && apple.getWeight() >= 160) {
        return true;
      } else {
        return false;
      }
    }
  }

  public static class YellowAndLessThan150Filter implements AppleFilter {

    @Override
    public boolean filter(Apple apple) {
      if (apple.getColor().equals("yellow") && apple.getWeight() < 150) {
        return true;
      } else {
        return false;
      }
    }
  }



  public static List<Apple> findApple(List<Apple> apples, AppleFilter filter) {
    List<Apple> list = new ArrayList<>();
    for (Apple apple :
        apples) {
      if (filter.filter(apple)) {
        list.add(apple);
      }
    }
    return list;
  }


  public static List<Apple> findGreenApple(List<Apple> apples) {
    List<Apple> list = new ArrayList<>();
    for (Apple apple :
        apples) {
      if ("green".equals(apple.getColor())) {
        list.add(apple);
      }
    }
    return list;
  }

  public static void main(String[] args) {
    List<Apple> list = Arrays.asList(Apple.builder().color("green").weight(150).build(),
        Apple.builder().color("yellow").weight(120).build(),
        Apple.builder().color("green").weight(170).build());
//    List<Apple> greenApples = findGreenApple(list);
//    assert greenApples.size() == 3 : "没有2个苹果";
//    System.out.println(greenApples);
    List<Apple> result = findApple(list, new GreenAndHigherThan160Filter());
    System.out.println(result);
    List<Apple> apple = findApple(list, new YellowAndLessThan150Filter());
    System.out.println(apple);
  }
}
