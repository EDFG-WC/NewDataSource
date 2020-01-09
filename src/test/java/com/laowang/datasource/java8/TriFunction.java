package com.laowang.datasource.java8;

@FunctionalInterface
public interface TriFunction<T, U, K, R> {

  R apply(T t, U u, K k);
}
