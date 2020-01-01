package com.laowang.datasource.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterApple {

  // 过滤的handler模式

  /**
   * 1 该注解只能标记在"有且仅有一个抽象方法"的接口上。 2 JDK8接口中的静态方法和默认方法，都不算是抽象方法。 3 接口默认继承java.lang.Object，所以如果接口显示声明覆盖了Object中方法，那么也不算抽象方法。
   * 4 该注解不是必须的，如果一个接口符合"函数式接口"定义，那么加不加该注解都没有影响。加上该注解能够更好地让编译器进行检查。如果编写的不是函数式接口，但是加上了@FunctionInterface，那么编译器会报错。
   */
  @FunctionalInterface
  public static interface AppleFilter {

    boolean filter(Apple apple);

    default void print(String var) {
      System.out.println(var);
    }
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

  public static void main(String[] args) throws InterruptedException {
    List<Apple> list = Arrays.asList(Apple.builder().color("green").weight(150).build(),
        Apple.builder().color("yellow").weight(120).build(),
        Apple.builder().color("green").weight(170).build());
    List<Apple> lambdaResult = findApple(list, apple -> apple.getColor().equals("green"));
    System.out.println(lambdaResult);

    Thread test = new Thread(() -> {
      System.out.println("你喜欢就好 " + Thread.currentThread().getName());
    }, "test");
    test.start();
    Thread.currentThread().join();
  }
}
