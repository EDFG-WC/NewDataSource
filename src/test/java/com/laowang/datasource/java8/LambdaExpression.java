package com.laowang.datasource.java8;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import org.apache.commons.collections4.Predicate;

public class LambdaExpression {

  public static void main(String[] args) {
    Comparator<Apple> byColor = new Comparator<Apple>() {
      @Override
      public int compare(Apple o1, Apple o2) {
        return o1.getColor().compareTo(o2.getColor());
      }
    };

    List<Apple> list = Collections.emptyList();
    list.sort(byColor);

    /**
     * 2和3完全一样, 但有return必须使用{}.
     */
    Comparator<Apple> byColor2 = (o1, o2) -> o1.getColor().compareTo(o2.getColor());
    Comparator<Apple> byColor3 = (o1, o2) -> {
      return o1.getColor().compareTo(o2.getColor());
    };

    Function<String, Integer> f = (String s) -> s.length();
    Predicate<Apple> filter = (Apple a) -> a.getColor().equals("green");
//   (int x, int y)->{
//      System.out.println(x);
//      System.out.println(y);
//    };

    Runnable r = () -> {
    };

    Function<Apple, Boolean> function = apple -> apple.getColor().equals("green");

    Supplier<Apple> supplier = Apple::new;
  }
}
