package com.laowang.datasource.guava.utilities;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import java.nio.charset.Charset;
import org.junit.Test;

public class StringTest {

  @Test
  public void testStringsMethod() {
    // emptyToNull("")会把一个空的字符串变成null
    assertThat(Strings.emptyToNull(""), nullValue());
    // nullToEmpty(null)会把null变成""
    assertThat(Strings.nullToEmpty(null), equalTo(""));
    // 如果不是null, 那就是给啥就是啥
    assertThat(Strings.nullToEmpty("hello"), equalTo("hello"));
    // commonPrefix()获得公共前缀
    assertThat(Strings.commonPrefix("fxck-you", "fxck-today"), equalTo("fxck-"));
    // commonSuffix()获得公共后缀
    assertThat(Strings.commonSuffix("fuck-you", "screw-you"), equalTo("-you"));
    // repeat("Alex", 3)返回一个String对象, 重复第一个参数第二个参数次
    assertThat(Strings.repeat("Alex", 3), equalTo("AlexAlexAlex"));
    // isNullOrEmpty()效果跟isBlank差不多, null或者""都会返回true
    assertThat(Strings.isNullOrEmpty(null), equalTo(true));
    assertThat(Strings.isNullOrEmpty(""), equalTo(true));

    // 判断第一个参数的长度够不够第二个参数的长度, 如果不够就在最前面补上第三个参数
    assertThat(Strings.padStart("Alex", 3, 'H'), equalTo("Alex"));
    assertThat(Strings.padStart("Alex", 5, 'H'), equalTo("HAlex"));
    // 跟上面的一样就是会把第三个参数补到最后
    assertThat(Strings.padEnd("Alex", 5, 'H'), equalTo("AlexH"));

  }

  @Test
  public void testCharsets() {
    // Charsets.UTF_8用来判断字符集的, 是一个枚举类型
    Charset charset = Charset.forName("UTF-8");
    assertThat(Charsets.UTF_8, equalTo(charset));
  }

  /**
   * functor
   */
  @Test
  public void testCharMatcher() {
    // 哇 所有方法都被抛弃了... javaDigit().matches('5')是用来判断字符是否属于数字的方法
    assertThat(CharMatcher.javaDigit().matches('5'), equalTo(true));
    assertThat(CharMatcher.javaDigit().matches('x'), equalTo(false));
    // CharMatcher.is('A').countIn("Alex Sharing the Google Guava to Us"), countIn返回的是is()方法里的参数出现在countIn里的次数
    assertThat(CharMatcher.is('A').countIn("Alex Sharing the Google Guava to Us"), equalTo(1));
    assertThat(CharMatcher.breakingWhitespace().collapseFrom("      hello Guava     ", '*'), equalTo("*hello*Guava*"));
    assertThat(CharMatcher.javaDigit().or(CharMatcher.whitespace()).removeFrom("hello 234 world"), equalTo("helloworld"));
    assertThat(CharMatcher.javaDigit().or(CharMatcher.whitespace()).retainFrom("hello 234 world"), equalTo(" 234 "));
  }
}
