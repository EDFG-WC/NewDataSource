package com.laowang.datasource.review;

public class Demo {
    public static void main(String[] args) {
        String gender = "male";
        final Factory factory = new Factory();
        factory.work(new Car("bmw", "male"), () -> gender);

        int i = 5;
        int j =10;
        System.out.println(i+~j);
    }
}
