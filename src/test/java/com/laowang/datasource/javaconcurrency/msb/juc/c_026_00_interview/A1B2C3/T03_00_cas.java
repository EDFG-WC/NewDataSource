package com.laowang.datasource.javaconcurrency.msb.juc.c_026_00_interview.A1B2C3;


public class T03_00_cas {

    enum ReadyToRun {T1, T2}

    /**
     * 不写的话, 保证不了立即可见, 会浪费CPU性能.
     */
    static volatile ReadyToRun r = ReadyToRun.T1; //思考为什么必须volatile

    public static void main(String[] args) {

        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        new Thread(() -> {

            for (char c : aI) {
                while (r != ReadyToRun.T1) {}
                System.out.print(c);
                r = ReadyToRun.T2;
            }

        }, "t1").start();

        new Thread(() -> {

            for (char c : aC) {
                while (r != ReadyToRun.T2) {}
                System.out.print(c);
                r = ReadyToRun.T1;
            }
        }, "t2").start();
    }
}


