package com.laowang.datasource.java8.stream;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// 数字类型的stream
public class NumericStream {

  public static void main(String[] args) {
    Stream<Integer> stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
    stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
    // 这个返回值是一个integer类型的, int只占用4bytes/32bits, 但integer占12bytes
    Stream<Integer> integerStream = stream.filter(integer -> integer > 3);
    Integer result2 = integerStream.reduce(0, Integer::sum);
    System.out.println(result2);
    // 这个返回值就是int, 可以占用更少的空间
    stream = Arrays.stream(new Integer[]{1, 2, 3, 4, 5, 6, 7});
    IntStream intStream = stream.mapToInt(Integer::intValue);
    int result = intStream.filter(integer -> integer > 3).sum();
    System.out.println(result);

    // a是9, 1到100之内所有符合勾股定理的数字组合:  a*a+b*b的和开平方结果一定是一个整数(%1==0), result是[a,b,c]
    int a = 9;
    IntStream resultStream = IntStream.rangeClosed(1, 100)
        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0);
    resultStream.forEach(System.out::println);
    // 能用数字类stream的时候, 就用数字类stream, 这样会省很多内存空间
    Stream<int[]> streamArray = IntStream.rangeClosed(1, 100)
        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).boxed()
        .map(x -> new int[]{a, x, (int) Math.sqrt(a * a + x * x)});
    streamArray.forEach(r -> System.out.println("a=" + r[0] + ", b=" + r[1] + ", c=" + r[2]));

    Stream<int[]> streamArray2 = IntStream.rangeClosed(1, 100)
        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
        .mapToObj(x -> new int[]{a, x, (int) Math.sqrt(a * a + x * x)});
    streamArray2.forEach(r -> System.out.println("a=" + r[0] + ", b=" + r[1] + ", c=" + r[2]));
  }
}
