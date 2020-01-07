package com.laowang.datasource.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaUsage {

  // 具体的方法是怎么做的怎么要求的, 都是在调用的时候传入的函数式的参数
  private static List<Apple> filter(List<Apple> source, Predicate<Apple> predicate) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : source) {
      if (predicate.test(apple)) {
        result.add(apple);
      }
    }
    return result;
  }

  private static List<Apple> filterByWeight(List<Apple> source, LongPredicate predicate) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : source) {
      if (predicate.test(apple.getWeight())) {
        result.add(apple);
      }
    }
    return result;
  }

  private static List<Apple> filterByBiPredicate(List<Apple> source,
      BiPredicate<String, Long> predicate) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : source) {
      if (predicate.test(apple.getColor(), apple.getWeight())) {
        result.add(apple);
      }
    }
    return result;
  }

  private static void simpleTestConsumer(List<Apple> source, Consumer<Apple> consumer) {
    for (Apple apple : source) {
      consumer.accept(apple);
    }
  }

  private static void simpleBiConsumer(List<Apple> source, BiConsumer<Apple, String> consumer,
      String c) {
    for (Apple apple : source) {
      consumer.accept(apple, c);
    }
  }

  // Function的泛型里第一个是实际上入参的类型, 第二个是返回值的类型, 也是函数式推导值的类型
  public static String testFunction(Apple apple, Function<Apple, String> fun) {
    return fun.apply(apple);
  }

  // BiFunction<String,Long,Apple> 前2个是参数类型, 后一个是返回值类型
  private static Apple testBiFunction(String color, Long weight,
      BiFunction<String, Long, Apple> fun) {
    return fun.apply(color, weight);
  }


  public static void main(String[] args) {
    Runnable runnable1 = () -> System.out.println("Hello1");
    Runnable runnable2 = new Runnable() {
      @Override
      public void run() {
        System.out.println("Hello2");
      }
    };
    process(runnable1);
    process(runnable2);
    process(() -> System.out.println("Hello3"));
    List<Apple> list = Arrays.asList(new Apple("green", 120), new Apple("red", 150));
    List<Apple> colorList = filter(list, apple -> apple.getColor().equals("green"));
    System.out.println(colorList);

    List<Apple> byWeight = filterByWeight(list, weight -> weight > 100);
    System.out.println(byWeight);

    List<Apple> biPredicateList = filterByBiPredicate(list,
        (color, weight) -> color.equals("green") && weight > 1);
    System.out.println(biPredicateList);

    simpleTestConsumer(list, apple -> System.out.println(apple));

    simpleBiConsumer(list,
        (apple, str) -> System.out.println(apple.getColor() + " " + str + " " + apple.getWeight()),
        "===");

    String blue = testFunction(new Apple("blue", 1000L), apple -> apple.toString());
    System.out.println(blue);

    IntFunction<Double> fun = i -> i * 1000d;
    double result = fun.apply(13);
    System.out.println(result);

    Apple white = testBiFunction("white", 1001L, (color, weight) -> new Apple(color, weight));
    System.out.println(white);

    Supplier<String> s = String::new;
    System.out.println(s.get().getClass());

    Apple purple = createApple(() -> new Apple("purple", 10));
    System.out.println(purple);

    int i = 0;
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        // i++; // 根据上下文推断, 在匿名内部类中, i被默认为是final修饰的, 所以不能使用++这种变更操作.
        System.out.println(i);
      }
    };
    // lambda表达式几乎等同于匿名内部类, 所以i也默认被final修饰
    Runnable runnable3 = () -> {
//      System.out.println(i++);
      System.out.println(i);
    };
  }

  private static void process(Runnable r) {
    r.run();
  }

  private static Apple createApple(Supplier<Apple> supplier) {
    return supplier.get();
  }
}

