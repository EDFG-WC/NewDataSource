package com.laowang.datasource.alg.bubblesort;

import org.junit.Test;

public class TestMethod {
  int[] array = {4, 5, 6, 3, 2, 1};

  @Test
  public void test() {
    babbleSort(array);
    for (int i : array) {
      System.out.println(i);
    }
  }

  private void babbleSort(int[] array) {
    if (array == null || array.length == 0) {
      return;
    }
    for (int i = 0; i < array.length; i++) {
      boolean isChanged = false;
      for (int j = 0; j < array.length - 1 - i; j++) {
        if (array[j] > array[j + 1]) {
          isChanged = true;
          int temp = array[j];
          array[j] = array[j + 1];
          array[j + 1] = temp;
        }
      }
      if (!isChanged) break;
    }
  }
}
