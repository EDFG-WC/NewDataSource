package com.laowang.datasource.guava.lru;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * not a thread-safe class
 *
 * @param <K>
 * @param <V>
 */
public class LinkedListLRUCache<K, V> implements LRUCache<K, V> {

  private final int limit;
  private final LinkedList<K> keys = new LinkedList<>();

  private final Map<K, V> cache = new HashMap<>();

  public LinkedListLRUCache(int limit) {
    Preconditions.checkArgument(limit > 0, "The limit is less than zero");
    this.limit = limit;
  }

  @Override
  public void put(K key, V value) {
    Preconditions.checkNotNull(key);
    Preconditions.checkNotNull(value);
    if (keys.size() >= limit) {
      K eldestKey = keys.removeFirst();
      cache.remove(eldestKey);
    }
    keys.addLast(key);
    cache.put(key, value);
  }

  @Override
  public V get(K key) {
    // 拿出来, 然后删掉原来的, 再放到最后面
    boolean isExist = keys.remove(key);
    if (!isExist) {
      return null;
    }
    keys.addLast(key);
    return cache.get(key);
  }

  @Override
  public void remove(K key) {
    boolean isExist = keys.remove(key);
    if (isExist) {
      cache.remove(key);

    }

  }

  @Override
  public int size() {
    return keys.size();
  }

  @Override
  public void clear() {
    this.keys.clear();
    this.cache.clear();
  }

  @Override
  public int limit() {
    return this.limit;
  }

  @Override
  public String toString() {

    StringBuilder builder = new StringBuilder();
    for (K k : keys) {
      builder.append(k).append("=").append(cache.get(k)).append(", ");
    }
    builder.setLength(builder.length() - 1);
    return builder.toString();
  }
}
