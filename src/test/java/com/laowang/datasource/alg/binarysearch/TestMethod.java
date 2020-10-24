package com.laowang.datasource.alg.binarysearch;

import org.junit.Test;

public class TestMethod {
  @Test
  public void test() {
    double result = binarySqrt(6, null);
    System.out.println(result);
  }

  public double binarySqrt(double n, Double precise) {
    double low = 0;
    double high = n;
    double prec = precise != null ? precise : 1e-7;
    double middle;
    double squre;
    while (high - low > prec) {
      middle = low + (high - low) / 2;
      squre = middle * middle;
      if (squre > n) {
        high = middle;
      } else {
        low = middle;
      }
    }
    return low + (high - low) / 2;
  }
}
