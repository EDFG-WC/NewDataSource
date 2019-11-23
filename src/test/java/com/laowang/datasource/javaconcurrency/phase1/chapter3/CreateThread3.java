package com.laowang.datasource.javaconcurrency.phase1.chapter3;

public class CreateThread3 {
    private int i = 0;
    private byte[] bytes = new byte[1024];
    private static int counter = 0;

    public static void main(String[] args) {
        int j = 0;
        int[] arr = new int[1024];
        int index = 0;
        try {
            add(index);
        } catch (Error e) {
            System.out.println(counter);
        }
    }

    /**
     * 压栈帧把栈压爆
     * @param index
     */
    private static void add(int index) {
        ++counter;
        add(index + 1);
    }
}
