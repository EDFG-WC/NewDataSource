package com.laowang.datasource.guava.utilities;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import com.google.common.base.Splitter;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.junit.Test;

public class SplitterTest {

  @Test
  public void testSplitOnSplit() {
    List<String> result = Splitter.on("|").splitToList("hello|world");
    assertThat(result, notNullValue());
    assertThat(result.size(), equalTo(2));
    assertThat(result.get(0), equalTo("hello"));
    assertThat(result.get(1), equalTo("world"));
  }

  @Test
  public void testSplitOnSplitOmitEmpty() {
    List<String> result = Splitter.on("|").splitToList("hello|world|||");
    assertThat(result, notNullValue());
    assertThat(result.size(), equalTo(5));
    // omitEmptyStrings()指的是根据分隔符得到的是空的内容时进行跳过.
    result = Splitter.on("|").omitEmptyStrings().splitToList("hello|world|||");
    assertThat(result, notNullValue());
    assertThat(result.size(), equalTo(2));
  }

  @Test
  public void testSplit_On_Split_OmitEmpty_TrimResult() {
    List<String> result = Splitter.on("|").omitEmptyStrings().splitToList("hello | world|||");
    assertThat(result, notNullValue());
    assertThat(result.size(), equalTo(2));
    assertThat(result.get(0), equalTo("hello "));
    assertThat(result.get(1), equalTo(" world"));
    // trimResults()的功能是把空格吞掉.
    result = Splitter.on("|").trimResults().omitEmptyStrings().splitToList("hello | world|||");
    assertThat(result.get(0), equalTo("hello"));
    assertThat(result.get(1), equalTo("world"));
  }

  /**
   * aaaabbbbccccdddd
   */
  @Test
  public void testSplitFixLength() {
    // fixedLength(x)的作用是把字符串按照x的长度分成若干个元素放进list
    List<String> result = Splitter.fixedLength(4).splitToList("aaaabbbbccccdddd");
    assertThat(result, notNullValue());
    assertThat(result.size(), equalTo(4));
    assertThat(result.get(0), equalTo("aaaa"));
    assertThat(result.get(3), equalTo("dddd"));
  }

  @Test
  public void testSplitOnSplitLimit() {
    // limit(x): 按照原来的规则, 分成x份, 第x个的时候后面无论能否匹配上规则, 都不会再分割了.
    List<String> result = Splitter.on("#").limit(3).splitToList("hello#world#java#google#scala");
    assertThat(result, notNullValue());
    assertThat(result.size(), equalTo(3));
    assertThat(result.get(0), equalTo("hello"));
    assertThat(result.get(1), equalTo("world"));
    assertThat(result.get(2), equalTo("java#google#scala"));
  }

  @Test
  public void testSplitOnPatternString() {
    // onPattern("\\|")的功能是根据正则表达式分割
    List<String> result = Splitter.onPattern("\\|").trimResults().omitEmptyStrings()
        .splitToList("hello | world|||");
    System.out.println(result);
    assertThat(result, notNullValue());
    assertThat(result.size(), equalTo(2));
    assertThat(result.get(0), equalTo("hello"));
    assertThat(result.get(1), equalTo("world"));
  }

  @Test
  public void testSplitOnPattern() {
    List<String> result = Splitter.on(Pattern.compile("\\|")).trimResults().omitEmptyStrings()
        .splitToList("hello | world|||");
    assertThat(result, notNullValue());
    assertThat(result.size(), equalTo(2));
    assertThat(result.get(0), equalTo("hello"));
    assertThat(result.get(1), equalTo("world"));
  }

  @Test
  public void testSplitOnSplitToMap() {
    // withKeyValueSeparator("="): 把=左右两边的内容识别成一个map, 这个符号是由我们定的
    Map<String, String> result = Splitter.on(Pattern.compile("\\|")).trimResults()
        .omitEmptyStrings().withKeyValueSeparator("=").split("hello=HELLO|world=WORLD|||");
    System.out.println(result);
    assertThat(result, notNullValue());
    assertThat(result.size(), equalTo(2));
    assertThat(result.get("hello"), equalTo("HELLO"));
    assertThat(result.get("world"), equalTo("WORLD"));

  }
}
