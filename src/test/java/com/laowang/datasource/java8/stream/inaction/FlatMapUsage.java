package com.laowang.datasource.java8.stream.inaction;

import com.alibaba.fastjson.JSONObject;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapUsage {

  public static void main(String[] args) {
    List<String> strs = Arrays.asList("好,好,学", "习,天,天", "向,上");
    // flatMap与map的区别在于 flatMap是将一个流中的每个值都转成一个个流，然后再将这些流扁平化成为一个流 。
    List<String> strList = strs.stream().map(str -> str.split(",")).flatMap(Arrays::stream)
        .collect(Collectors.toList());
    System.out.println("strList => " + strList);
  }
}
