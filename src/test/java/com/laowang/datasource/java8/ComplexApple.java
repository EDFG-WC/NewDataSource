package com.laowang.datasource.java8;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ComplexApple {
  private String color;
  private long weight;
  private String name;
}
