package com.laowang.datasource.javaconcurrency.phase1.chapter2.strategy;

public class TaxCalculatorMain {
    public static void main(String[] args) {
        TaxCalculator taxCalculator = new TaxCalculator(10000d, 2000d,((salary, bonus) -> salary * 0.1 + bonus * 0.15));
        System.out.println(taxCalculator.calculate());
    }
}
