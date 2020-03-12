package com.laowang.datasource.guava.lru;

import org.junit.Test;

public class Example {

  @Test
  public void test1() {
    LinkedHashLRUCache<String, String> cache = new LinkedHashLRUCache<>(3);
    cache.put("1", "1");
    cache.put("2", "2");
    cache.put("3", "3");
    System.out.println(cache);
    cache.put("4", "4");
    System.out.println(cache);
    System.out.println(cache.get("2"));
    System.out.println(cache);
  }

  @Test
  public void test2() {
    LinkedListLRUCache<String, String> cache = new LinkedListLRUCache(3);
    cache.put("1", "1");
    cache.put("2", "2");
    cache.put("3", "3");
    System.out.println(cache);
    cache.put("4", "4");
    System.out.println(cache);
    System.out.println(cache.get("2"));
    System.out.println(cache);
  }
}
