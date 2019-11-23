package com.laowang.datasource.javaconcurrency.phase1.chapter7;

public class SynchronizedStaticTest {
    public static void main(String[] args) {
        new Thread("T1"){
            @Override
            public void run() {
                SynchronizedStatic.method1();
            }
        }.start();

        new Thread("T2"){
            @Override
            public void run() {
                SynchronizedStatic.method2();
            }
        }.start();

        new Thread("T3"){
            @Override
            public void run() {
                SynchronizedStatic.method3();
            }
        }.start();
    }
}
