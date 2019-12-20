package com.laowang.datasource.java8;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Apple {

  private String color;
  private long weight;
}
