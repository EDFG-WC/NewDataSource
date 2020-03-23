package com.laowang.datasource.review.day4;

import java.util.Arrays;
import java.util.List;

public class Example {
    String str = new String("good");
    List<Integer> list = Arrays.asList(1, 2, 3);
    char[] ch = {'a', 'b', 'c'};

    public static void main(String[] args) {
        Example ex = new Example();
        ex.change(ex.str, ex.ch, ex.list);
        System.out.println(ex.str);
        System.out.println(ex.ch);
        System.out.println(ex.list);
    }

    public void change(String str, char ch[], List value) {
        str = "test ok";
        ch[0] = 'g';
        value.set(0, 15);
    }
}