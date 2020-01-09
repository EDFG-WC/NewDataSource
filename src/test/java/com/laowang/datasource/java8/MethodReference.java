package com.laowang.datasource.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReference {

  public static void main(String[] args) {
    Consumer<String> consumer = s -> System.out.println(s);
    useConsumer(consumer, "Hello Alex.");
    // 这种写法等价函数式的consumer
    useConsumer(System.out::println, "Hello wangchen.");

    List<Apple> list = Arrays
        .asList(new Apple("abc", 123), new Apple("Green", 110), new Apple("red", 123));

    System.out.println(list);

    // 写一个排序的:
    list.sort((a1, a2) -> a1.getColor().compareTo(a2.getColor()));
    // 在方法引用里可以写成:
    list.sort(Comparator.comparing(Apple::getColor));
    System.out.println(list);
    System.out.println("=====");
    list.stream().forEach(apple -> System.out.println(apple));
    System.out.println("再一次看到, 方法推导和::是等价的");
    list.stream().forEach(System.out::println);

    int value = Integer.parseInt("123");
    // 前一个方法推导的入参, 后一个是返回值
    Function<String, Integer> function1 = Integer::parseInt;
    Integer apply = function1.apply("123");
    System.out.println(apply);

    System.out.println("======");
    // <>里前2个是参数, 后1个是返回值类型, function2和3是完全等价的, 这里我们终于得出结论, 方法引用就是某种lambda表达式的简化
    BiFunction<String, Integer, Character> function2 = String::charAt;
    BiFunction<String, Integer, Character> function3 = (s, index) -> s.charAt(index);
    Character character1 = function2.apply("Hello", 2);
    System.out.println(character1);

    System.out.println("=====");
    String str = "Hello";
    Function<Integer, Character> function4 = x->str.charAt(x);
    Function<Integer, Character> function5 = str::charAt;
    Character character2 = function4.apply(4);
    System.out.println(character2);

    System.out.println("=====");
    // 这样没有任何的输出....
    Runnable runnable = String::new;
    runnable.run();
    Supplier<String> supplier = String::new;
    String s = supplier.get();
    System.out.println(s);

    System.out.println("=====");
    // 有输出的来啦:
    BiFunction<String,Long,Apple> appleBiFunction = Apple::new;
    Apple rainbow = appleBiFunction.apply("rainbow", 5L);
    System.out.println(rainbow);

    TriFunction<String,Long,String,ComplexApple> triFunction = ComplexApple::new;
    ComplexApple complexApple = triFunction.apply("Pink", 999L, "Fuji");
    System.out.println(complexApple);
  }

  private static <T> void useConsumer(Consumer<T> consumer, T t) {
    // 执行2遍
    consumer.accept(t);
    consumer.accept(t);
  }
}
