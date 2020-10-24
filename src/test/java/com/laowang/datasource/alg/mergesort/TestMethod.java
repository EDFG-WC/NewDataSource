package com.laowang.datasource.alg.mergesort;

import org.junit.Test;

import java.util.Arrays;

public class TestMethod {
  int[] a = {11, 8, 3, 9, 7, 1, 2, 5};

  @Test
  public void test() {
        mergeSort(a, 0, a.length - 1);
        for (int val : a) {
          System.out.println(val);
        }
//    int[] result = javaMergeSort(a);
//    for (int val : result) {
//      System.out.println(val);
//    }
  }

  private void mergeSort(int[] a, int p, int r) {
    if (p >= r) return;
    int q = (p + r) / 2;
    mergeSort(a, p, q);
    mergeSort(a, q + 1, r);
    merge(a, p, q, r);
  }

  private void merge(int[] a, int p, int q, int r) {
    int[] tmp = new int[r - p + 1];
    int i = p;
    int j = q + 1;
    int k = 0;
    while (i <= q && j <= r) {
      if (a[i] <= a[j]) {
        tmp[k++] = a[i++];
      } else {
        tmp[k++] = a[j++];
      }
    }
    int start = i;
    int end = q;
    if (j <= r) {
      start = j;
      end = r;
    }
    while (start <= end) {
      tmp[k++] = a[start++];
    }
    for (int l = 0; l < r - p; l++) {
      a[l] = tmp[l];
    }
  }

  private int[] javaMergeSort(int[] array) {
    int[] arr = Arrays.copyOf(array, array.length);
    // 小于2没有排序的意义了
    if (arr.length < 2) {
      return arr;
    }
    int middle = arr.length / 2;
    int[] left = javaMergeSort(Arrays.copyOfRange(arr, 0, middle));
    int[] right = javaMergeSort(Arrays.copyOfRange(arr, middle, arr.length));
    return javaMerge(left, right);
  }

  private int[] javaMerge(int[] left, int[] right) {
    int[] result = new int[left.length + right.length];
    int i = 0;
    while (left.length > 0 && right.length > 0) {
      if (left[0] <= right[0]) {
        result[i++] = left[0];
        left = Arrays.copyOfRange(left, 1, left.length);
      } else {
        result[i++] = right[0];
        right = Arrays.copyOfRange(right, 1, right.length);
      }
    }
    while (left.length > 0) {
      result[i++] = left[0];
      left = Arrays.copyOfRange(left, 1, left.length);
    }
    while (right.length > 0) {
      result[i++] = right[0];
      right = Arrays.copyOfRange(right, 1, right.length);
    }
    return result;
  }

  public static void main(String[] args) {
    System.out.println(3 / 2);
    System.out.println((int) Math.floor(3 / 2));
  }
}
