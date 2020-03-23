package com.laowang.datasource.review;

public class Car {
    private String brand;
    private String color;

    public Car(String brand, String color) {
        this.brand = brand;
        this.color = color;
    }

    public void wash() {
        System.out.println("洗车");
        boolean b = true?false:1==1;
        System.out.println(b);
    }
}
