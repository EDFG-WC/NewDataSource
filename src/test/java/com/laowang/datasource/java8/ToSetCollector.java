package com.laowang.datasource.java8;


import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToSetCollector<T> implements Collector<T, Set<T>, Set<T>> {

  private void log(final String log) {
    System.out.println(Thread.currentThread().getName() + "---" + log);
  }

  @Override
  public Supplier<Set<T>> supplier() {
    log("supplier");
    return TreeSet::new;
  }

  @Override
  public BiConsumer<Set<T>, T> accumulator() {
    log("accumulator");
    return Set::add;
  }

  // 这个操作是源于fork join, 在fork join的时候一开始的对象被分成了很多个, 所以要在这里combiner一下
  @Override
  public BinaryOperator<Set<T>> combiner() {
    log("combiner");
    return (set1, set2) -> {
      set1.addAll(set2);
      return set1;
    };
  }

  @Override
  public Function<Set<T>, Set<T>> finisher() {
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
