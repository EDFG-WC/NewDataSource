package com.laowang.datasource.alg.strbm;

public class TestMethod {
  private static final int SIZE = 256;

  /**
   * 创建一个散列表, 存储坏字符在模式串中出现的位置(相同元素只存靠后的位置)
   *
   * @param patternChars 模式串
   * @param length 模式串长度
   * @param charPosition 坏字符散列表
   */
  private void generateBadChars(char[] patternChars, int length, int[] charPosition) {
    // 初始化
    for (int i = 0; i < SIZE; ++i) {
      charPosition[i] = -1;
    }
    for (int i = 0; i < length; i++) {
      int ascii = (int) patternChars[i]; // 计算模式串每个字符的ascii码
      charPosition[ascii] = i; // 模式串中不存在的就是-1, 存在的字符在对应的ascii码的索引位置存了一个模式串索引
    }
  }

  private int bm(char[] target, int tLength, char[] pattern, int pLength) {
    int[] charPos = new int[SIZE]; // 记录模式串中每个字符最后出现的位置
    generateBadChars(pattern, pLength, charPos); // 构建坏字符哈希表
    int i = 0; // 表示主串与模式串对齐的第一个字符
    while (i <= tLength - pLength) { // 从长度上讲, 排除了最后一个部分, 长度不够, 不可能匹配出结果
      int j; // 模式串从后往前匹配的索引
      for (j = pLength - 1; j >= 0; j--) { // 模式串从后往前匹配
        if (pattern[j] != target[i + j]) break; // 坏字符没有匹配上, 跳出循环
      }
      if (j < 0) {
        return i; // 匹配成功, 因为for循环的特性, 除开表达式1赋值, for循环执行的语句顺序是:表达式2, 循环体, 表达式3,
        // 如果循环体中的break语句没有被执行过,
        // j会因为表达式3自减到-1, 然后执行表达式2, 判断为false, 然后跳出循环. 此时j是-1;
      }
      // 将模式串往后滑动j-charPos[(int)target[i+j]位
      i = (j - charPos[(int) pattern[i + j]]);
    }
    return -1;
  }

  /**
   * 好后缀规则准备, 就是把后缀数组和前缀数组填满(下标都是长度), 后缀(suffix)数组存储自己在模式串中重复的位置(没有重复就是-1),
   * 前缀(prefix)数组则存储某个长度的后缀子串在前缀中是否存在重复
   *
   * @param pattern
   * @param length
   * @param suffix
   * @param prefix
   */
  private void generateGoodSuffix(char[] pattern, int length, int[] suffix, boolean[] prefix) {
    for (int i = 0; i < length; i++) {
      suffix[i] = -1;
      prefix[i] = false;
    }
    for (int i = 0; i < length - 1; i++) {
      int j = i; // j表示公共后缀子串的起始下标
      int k = 0; // 公共后缀的子串长度(应为子串是固定的, 所以通过长度就可以确定它内容是什么了)
      while (j >= 0 && pattern[j] == pattern[length - 1 - k]) { // cabcab: 第一次进这个数组的应该是patter[2]==patten[5]
        --j;
        ++k;
        suffix[k] = j + 1; // j+1 表示公共后缀子串在 b[0, i] 中的起始下标
      }
      if (j == -1) prefix[k] = true; // 减到-1就可以为true了?
    }
  }
}
