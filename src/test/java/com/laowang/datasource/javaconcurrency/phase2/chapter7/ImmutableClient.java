package com.laowang.datasource.javaconcurrency.phase2.chapter7;

import java.util.stream.IntStream;

public class ImmutableClient {

  public static void main(String[] args) {
    Person person = new Person("Alex", "Gansu");
    IntStream.range(0, 5).forEach(i -> new UsePersonThread(person).start());
  }
}
