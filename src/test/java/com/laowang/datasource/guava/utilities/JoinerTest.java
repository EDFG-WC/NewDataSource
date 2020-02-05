package com.laowang.datasource.guava.utilities;

import static com.google.common.collect.ImmutableMap.of;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import com.google.common.base.Joiner;
import com.google.common.io.Files;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.Test;

public class JoinerTest {

  private final List<String> strList = Arrays.asList("Google", "Guava", "Java", "Scala", "Kafka");
  private final List<String> strListWithNull = Arrays
      .asList("Google", "Guava", "Java", "Scala", null);
  private final Map<String, String> stringMap = of("Hello", "Guava", "Java", "Scala");
  private final String targetFileName = "F:\\workspace\\guava-joiner.txt";
  private final String targetFileNameToMap = "F:\\workspace\\guava-joiner-map.txt";


  @Test
  public void testJoinOnJoin() {
    String result = Joiner.on("#").join(strList);
    assertThat(result, equalTo("Google#Guava#Java#Scala#Kafka"));
    System.out.println(result);
  }

  @Test(expected = NullPointerException.class)
  public void testJoinOnJoinWithNullValue() {
    String result = Joiner.on("#").join(strListWithNull);
    assertThat(result, equalTo("Google#Guava#Java#Scala#Kafka"));
  }

  @Test
  public void testJoinOnJoinWithNullValueButSkip() {
    String result = Joiner.on("#").skipNulls().join(strListWithNull);
    System.out.println(result);
  }

  @Test
  public void testJoinOnJoinWithNullValueButUseDefaultValue() {
    // 遇到null用什么代替
    String result = Joiner.on("#").useForNull("Default").join(strListWithNull);
    System.out.println(result);
  }

  @Test
  public void testJoinOnAppendToStringBuilder() {
    final StringBuilder builder = new StringBuilder();
    StringBuilder stringBuilder = Joiner.on("#").useForNull("Default")
        .appendTo(builder, strListWithNull);
    // builder和stringBuilder的区别: 完全没区别.
    assertThat(builder, sameInstance(stringBuilder));
    assertThat(builder.toString(), equalTo("Google#Guava#Java#Scala#Default"));
    // 消除歧义的正确用法:
    StringBuilder oneLessBuilder = Joiner.on("#").useForNull("Fucker")
        .appendTo(new StringBuilder(), strListWithNull);
    System.out.println(oneLessBuilder);
  }

  @Test
  public void testJoinOnAppendToWriter() {
    try (FileWriter writer = new FileWriter(targetFileName)) {
      Joiner.on("#").useForNull("Default")
          .appendTo(writer, strListWithNull);
      assertThat(Files.isFile().test(new File(targetFileName)), equalTo(true));
    } catch (IOException e) {
      fail("some thing wrong in appending file.");
    }
  }

  @Test
  public void testJoinByStreamSkipNullValues() {
    final String result = strListWithNull.stream().filter(item -> item != null && !item.isEmpty())
        .collect(Collectors.joining("#"));
    System.out.println(result);
  }

  @Test
  public void testJoinByStreamWithDefaultValues() {
    final String result = strListWithNull.stream()
        .map(item -> item == null || item.isEmpty() ? "Default" : item).collect(
            Collectors.joining("#"));
    System.out.println(result);
  }

  @Test
  public void testJoinWithMap() {
    final String result = Joiner.on("#").withKeyValueSeparator("=").join(stringMap);
    System.out.println(result);
  }

  @Test
  public void testJoinOnWithMapToAppendable() {
    try (FileWriter writer = new FileWriter(targetFileNameToMap)) {
      Joiner.on("#").withKeyValueSeparator("=")
          .appendTo(writer, stringMap);
      assertThat(Files.isFile().test(new File(targetFileNameToMap)), equalTo(true));
    } catch (IOException e) {
      fail("some thing wrong in appending file.");
    }
  }
}

