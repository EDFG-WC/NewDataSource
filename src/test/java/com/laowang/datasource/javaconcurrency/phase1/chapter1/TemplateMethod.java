package com.laowang.datasource.javaconcurrency.phase1.chapter1;

public class TemplateMethod {
    public final void print(String message) {
        System.out.println("#####");
        wrapPrint(message);
        System.out.println("#####");
    }

    protected void wrapPrint(String message) {
        System.out.println(message);
    }
}
