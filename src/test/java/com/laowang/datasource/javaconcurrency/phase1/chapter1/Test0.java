package com.laowang.datasource.javaconcurrency.phase1.chapter1;

public class Test0 {
    public static void main(String[] args) {
        TemplateMethod method1 = new TemplateMethod() {
            @Override protected void wrapPrint(String message) {
                System.out.println("*" + message + "*");
            }
        };
        method1.print("Hello Thread");

        TemplateMethod method2 = new TemplateMethod() {
            @Override protected void wrapPrint(String message) {
                System.out.println("?" + message + "?");
            }
        };
        method2.print("Hello Thread");
    }
}
