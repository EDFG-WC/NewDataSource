package com.laowang.datasource.javaconcurrency.phase2.chapter11.simpleSafeBox;

import lombok.Data;

/**
 * 用来桥接2种不同查询的JavaBean
 */
@Data
public class Context {

  private String name;
  private String cardId;
}
