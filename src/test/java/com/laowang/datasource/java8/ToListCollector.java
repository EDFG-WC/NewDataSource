package com.laowang.datasource.java8;


import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

  private void log(final String log) {
    System.out.println(Thread.currentThread().getName() + "---" + log);
  }

  @Override
  public Supplier<List<T>> supplier() {
    log("supplier");
    return ArrayList::new;
  }

  @Override
  public BiConsumer<List<T>, T> accumulator() {
    log("accumulator");
    return List::add;
  }

  // 这个操作是源于fork join, 在fork join的时候一开始的对象被分成了很多个, 所以要在这里combiner一下
  @Override
  public BinaryOperator<List<T>> combiner() {
    log("combiner");
    return (list1, list2) -> {
      list1.addAll(list2);
      return list1;
    };
  }

  @Override
  public Function<List<T>, List<T>> finisher() {
    log("finisher");
    // Function.identity();等价于t->t;
    return Function.identity();
  }

  // 处理一些特征值
  @Override
  public Set<Characteristics> characteristics() {
    log("characteristics");
    return Collections
        .unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT));
  }
}
