package com.laowang.datasource.JavaConcurrency.chapter2.Strategy;

public class TaxCalculator {

    private final double salary;

    private final double bonus;

    private final CalculatorStrategy calculatorStrategy;

    public TaxCalculator(double salary, double bonus, CalculatorStrategy calculatorStrategy) {
        this.salary = salary;
        this.bonus = bonus;
        this.calculatorStrategy = calculatorStrategy;
    }

    //类似run方法
    protected double calcTax() {
        return calculatorStrategy.calculate(salary, bonus);
    }

    //类似start方法
    public double calculate() {
        return calcTax();
    }

    //被定义为final之后不能设置set方法
    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }
}
