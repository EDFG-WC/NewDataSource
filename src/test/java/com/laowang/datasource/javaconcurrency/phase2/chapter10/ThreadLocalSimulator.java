package com.laowang.datasource.javaconcurrency.phase2.chapter10;

import java.util.HashMap;
import java.util.Map;

/**
 * 始终以当前线程作为key值.
 * @param <T>
 */
public class ThreadLocalSimulator<T> {

  private final Map<Thread, T> stroge = new HashMap<>();

  public void set(T t) {
    synchronized (this) {
      Thread key = Thread.currentThread();
      stroge.put(key, t);
    }
  }

  public T get() {
    synchronized (this) {
      Thread key = Thread.currentThread();
      T value = stroge.get(key);
      if (value == null) {
        return initValue();
      }
      return value;
    }
  }

  public T initValue() {
    return null;
  }
}
