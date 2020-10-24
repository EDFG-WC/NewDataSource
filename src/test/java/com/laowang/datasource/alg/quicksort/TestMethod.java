package com.laowang.datasource.alg.quicksort;

import org.junit.Test;

public class TestMethod {
  int[] a = {11, 8, 3, 9, 7, 1, 2, 5};

  @Test
  public void test() {
    int[] sortedArray = quickSort(a, 0, a.length - 1);
    for (int a : sortedArray) {
      System.out.println(a);
    }
  }

  private int[] quickSort(int[] a, int leftIndex, int rightIndex) {
    if (leftIndex < rightIndex) {
      int index = partition(a, leftIndex, rightIndex);
      quickSort(a, leftIndex, index - 1);
      quickSort(a, index + 1, rightIndex);
    }
    return a;
  }

  private int partition(int[] a, int leftIndex, int rightIndex) {
    int pivot = a[rightIndex];
    int index = leftIndex;
    for (int i = leftIndex; i < rightIndex; i++) {
      if (a[i] < pivot) {
        swap(a, i, index);
        index++;
      }
    }
    swap(a, index, rightIndex);
    return index;
  }

  private void swap(int[] a, int i, int j) {
    int tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }
}
