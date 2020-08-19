package com.laowang.datasource.alg.stages;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestMethod {
  Map<Integer, Integer> map = new HashMap<>();

  @Test
  public void test() {
    int ways = waysOfReachTheEnd(7);
    System.out.println(ways);
  }

  private int waysOfReachTheEnd(int stages) {
    if (stages == 1) return 1;
    if (stages == 2) return 2;
    if (map.containsKey(stages)) return map.get(stages);
    int ret = waysOfReachTheEnd(stages - 1) + waysOfReachTheEnd(stages - 2);
    map.put(stages, ret);
    return ret;
  }

}
