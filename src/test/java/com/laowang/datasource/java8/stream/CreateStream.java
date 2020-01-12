package com.laowang.datasource.java8.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CreateStream {

  public static void main(String[] args) {
//    createStreamFromCollections().forEach(System.out::println);
//    createStreamFromValues().forEach(System.out::println);
//    createStreamFromArrays().forEach(System.out::println);
//    createStreamFromFile();
//    createStreamFromIterator().forEach(System.out::println);
//    createStreamFromGenerate().forEach(System.out::println);
    createObjStreamFromGenerate().forEach(System.out::println);
  }

  private static Stream<String> createStreamFromCollections() {
    // list的顺序是不会被stream打乱的(前提是不用sort).
    List<String> list = Arrays.asList("hello", "alex", "wangwenjun", "world", "stream");
    return list.stream();
  }

  private static Stream<String> createStreamFromValues() {
    return Stream.of("hello", "alex", "wangwenjun", "world", "stream");
  }

  private static Stream<String> createStreamFromArrays() {
    String[] strings = {"hello", "alex", "wangwenjun", "world", "stream"};
    return Arrays.stream(strings);
  }

  private static Stream<String> createStreamFromFile() {
    Path path = Paths.get(
        "F:\\workspace\\NewDataSource\\src\\test\\java\\com\\laowang\\datasource\\java8\\stream\\SimpleStream.java");
    try (Stream<String> lines = Files.lines(path)) {
      lines.forEach(System.out::println);
      return lines;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  // 无限stream
  private static Stream<Integer> createStreamFromIterator() {
    Stream<Integer> stream = Stream.iterate(0, n -> n + 2).limit(15);
    return stream;
  }

  // 无限stream
  private static Stream<Double> createStreamFromGenerate() {
    return Stream.generate(Math::random).limit(10);
  }

  // 一个JavaBean
  static class Obj {

    private int id;
    private String name;

    public Obj(int id, String name) {
      this.id = id;
      this.name = name;
    }

    public int getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    @Override
    public String toString() {
      return "Obj{" +
          "name='" + name + '\'' +
          ", id=" + id +
          '}';
    }
  }

  static class ObjSupplier implements Supplier<Obj> {

    private int index = 0;
    private Random random = new Random(System.currentTimeMillis());

    @Override
    public Obj get() {
      index = random.nextInt(100);
      return new Obj(index, "Name->" + index);
    }
  }

  private static Stream<Obj> createObjStreamFromGenerate() {
    // generate的入参类型是supplier
    return Stream.generate(new ObjSupplier()).limit(15);
  }
}
