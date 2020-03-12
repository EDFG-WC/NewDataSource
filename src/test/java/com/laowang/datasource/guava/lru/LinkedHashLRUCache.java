package com.laowang.datasource.guava.lru;

import com.google.common.base.Preconditions;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * not a thread-safe class
 *
 * @param <K>
 * @param <V>
 */
public class LinkedHashLRUCache<K, V> implements LRUCache<K, V> {

  // 这个内部类, 不会暴露给外部, 封装得足够好
  private static class InternalLRUCache<K, V> extends LinkedHashMap<K, V> {

    private final int limit;

    public InternalLRUCache(int limit) {
      super(16, 0.75f, true);
      this.limit = limit;
    }

    // 重写这个算法意味着超过limit数量的entry就会被删掉. 就算不重写, 给予lru的排序功能还是能够实现的
    @Override
    protected boolean removeEldestEntry(Entry<K, V> eldest) {
      // 删掉老entry的前提:
      return size() > limit;
    }
  }

  private final int limit;
  private final InternalLRUCache internalLRUCache;

  public LinkedHashLRUCache(int limit) {
    Preconditions.checkArgument(limit > 0, "The limit is less than zero");
    this.limit = limit;
    this.internalLRUCache = new InternalLRUCache<>(limit);
  }

  @Override
  public void put(K key, V value) {
    this.internalLRUCache.put(key, value);
  }

  @Override
  public V get(K key) {
    return (V) this.internalLRUCache.get(key);
  }

  @Override
  public void remove(K key) {
    this.internalLRUCache.remove(key);
  }

  @Override
  public int size() {
    return this.internalLRUCache.size();
  }

  @Override
  public void clear() {
    this.internalLRUCache.clear();
  }

  @Override
  public int limit() {
    return this.internalLRUCache.limit;
  }

  @Override
  public String toString() {
    return internalLRUCache.toString();
  }
}
