package com.laowang.datasource.guava.cache;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

public class CacheLoaderTest {

  private boolean isFromDB = false;

  @Test
  public void testBasic() throws ExecutionException, InterruptedException {
    LoadingCache<String, Employee> cache = CacheBuilder.newBuilder().maximumSize(10)
        .expireAfterAccess(30, TimeUnit.MILLISECONDS).build(createCacheLoader());
    // 本身缓存里是没有Alex这个雇员的, 但重写的方法告诉了程序如何去拿Alex雇员的信息(findEmployeeByName方法). 第二次之后, 再这么做就不是从DB里拿的, 而是从缓存里拿的了.
    Employee employee = cache.get("Alex");
    assertThat(employee, notNullValue());
    assertLoadedFromDBThenResetTheFlag();
    employee = cache.get("Alex");
    assertThat(employee, notNullValue());
    assertLoadedFromCache();

    // 通过设置过期时间, 缓存里的值只保存了30毫秒, 休眠之后再get的值就是从数据库从新查出来的了
    TimeUnit.MILLISECONDS.sleep(31);
    employee = cache.get("Alex");
    assertThat(employee, notNullValue());
    assertLoadedFromDBThenResetTheFlag();
  }

  @Test
  public void testEvacuationBySize() {
    /* size指的是当前缓存对象的容量 */
    CacheLoader<String, Employee> cacheLoader = createCacheLoader();
    LoadingCache<String, Employee> cache = CacheBuilder.newBuilder().maximumSize(3)
        .build(cacheLoader);
    cache.getUnchecked("Alex");
    assertLoadedFromDBThenResetTheFlag();
    cache.getUnchecked("Jack");
    assertLoadedFromDBThenResetTheFlag();
    cache.getUnchecked("Gavin");
    assertLoadedFromDBThenResetTheFlag();
    assertThat(cache.size(), equalTo(3L));
    cache.getUnchecked("Susan");
    assertThat(cache.getIfPresent("Alex"), nullValue());
    assertThat(cache.getIfPresent("Susan"), notNullValue());
  }

  @Test
  public void testEvacuationByWeight() {
    /* weight有一个特定的衡量方式, 我们可以创建Weight类来设定 */
    CacheLoader<String, Employee> cacheLoader = createCacheLoader();
    Weigher<String, Employee> weigher = (key, employee) -> employee.getName().length() + employee
        .getDept().length() + employee.getEmpId()
        .length();
    LoadingCache<String, Employee> cache = CacheBuilder.newBuilder().maximumWeight(45)
        .concurrencyLevel(1)
        .weigher(weigher)
        .build(cacheLoader);

    cache.getUnchecked("Gavin");
    assertLoadedFromDBThenResetTheFlag();
    cache.getUnchecked("Kevin");
    assertLoadedFromDBThenResetTheFlag();
    cache.getUnchecked("Allena");
    assertLoadedFromDBThenResetTheFlag();
    assertThat(cache.size(), equalTo(2L));
//    assertThat(cache.getIfPresent("Gavin"), notNullValue());
//
//    cache.getUnchecked("Jason");
//    assertThat(cache.getIfPresent("Kevin"), nullValue());
//    assertThat(cache.size(), equalTo(3L));
  }


  private void assertLoadedFromDBThenResetTheFlag() {
    assertThat(true, equalTo(isFromDB));
    this.isFromDB = false;
  }

  private void assertLoadedFromCache() {
    assertThat(false, equalTo(isFromDB));
  }

  private Employee findEmployeeByName(final String name) {
    isFromDB = true;
    return new Employee(name, name, name);
  }

  private CacheLoader<String, Employee> createCacheLoader() {
    return new CacheLoader<String, Employee>() {
      @Override
      public Employee load(String key) throws Exception {
        return findEmployeeByName(key);
      }
    };
  }
}
