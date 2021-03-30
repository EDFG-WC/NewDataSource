package com.laowang.datasource.javaconcurrency.msb.juc.c_022_RefTypeAndThreadLocal;

import java.io.IOException;

public class T01_NormalReference {
    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;
        System.gc(); //DisableExplicitGC

        // 没有任何意义， 阻塞住方法不结束.
        System.in.read();
    }
}
