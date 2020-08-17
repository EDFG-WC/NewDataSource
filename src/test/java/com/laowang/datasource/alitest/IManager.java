package com.laowang.datasource.alitest;

public interface IManager {
  default void printB() {
    System.out.println("iManager printB");
  }
}
