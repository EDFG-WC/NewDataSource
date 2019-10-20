package com.laowang.datasource.JavaConcurrency.chapter3;

public class CreateThread5 {
    private int i = 0;
    private byte[] bytes = new byte[1024];
    private static int counter = 0;

    public static void main(String[] args) {

        Thread t1 = new Thread(null, new Runnable() {
            @Override
            public void run() {
                try {
                    add(1);
                } catch (Error e) {
                    System.out.println(counter);
                }
            }
        }, "Test", 1 << 24);
        t1.start();
    }

    /**
     * 压栈帧把栈压爆
     *
     * @param index
     */
    private static void add(int index) {
        ++counter;
        add(index + 1);
    }
}
