package com.laowang.datasource.alg.insertSorting;

import org.junit.Test;

public class TestMethod {

  int[] array = {4, 5, 6, 1, 3, 2};

  @Test
  public void test() {
//    insertSorting(array, array.length);
    secondTry(array);
    for (int i = 0; i < array.length; i++) {
      System.out.println(array[i]);
    }
  }

  private void insertSorting(int[] array, int length) {
    // 从1开始: 第0个元素不需要排序. 第0个元素天生就属于排序区(假设)
    for (int i = 1; i < length; i++) { // i是排序区的索引, 不需要从0开始
      int value = array[i];
      int j = i - 1; // j就是排序区的最大索引
      for (; j >= 0; j--) { // 从后往前查找插入的位置
        if (array[j] > value) {
          array[j + 1] = array[j]; // 数据向前移动一个索引, array[j]的位置空出来了(实际上是可以随便修改了)
        } else {
          break; // 假设排序区都是有序的, array[j]是最大的, 连最大的都小于等于value, 那就不用往下了, 直接跳出循环
        }
      }
      // 如果array[j] > value不成立, break运行 j--就不会运行, 所以j大小不变, array[j+1]就是value, 自己赋值给自己
      // 如果成立, j--运行, for循环开始, 产生了数据搬移, j+1让指针回到了j的位置上, value被插入了j的位置上
      array[j + 1] = value; // 当给数据1排序的时候, j为-1.
    }
  }

  private void secondTry(int[] array) {
    for (int i = 1; i < array.length; i++) {
      int value = array[i];
      int j = i - 1;
      for (; j >= 0; j--) {
        if (array[j] > value) {
          array[j + 1] = array[j];
        } else {
          break;
        }
      }
      array[j + 1] = value;
    }
  }
}
