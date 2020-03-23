package com.laowang.datasource.review;

import org.apache.ibatis.javassist.runtime.Inner;

public class Main {
    private int m;

    public int inc() {
        return m + 1;
    }

    Inner inner = new Inner() {
        public void function() {
            System.out.println("function");
        }
    };
}

