package com.laowang.classloader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFinal {

    final int age;
    {
        age=22;
        System.out.println(age);
    }

    public static void main(String[] args) {
//        StringBuilder builder = new StringBuilder("test");
//        StringBuilder builder1 = testFinal(builder);
//        System.out.println(builder1.toString());
        Map<String, String> map = new HashMap<String, String>();
        map.put("final", "final");
//        System.out.println(changeValue(map).get("final"));
        System.out.println(change(map).get("final"));
//        System.out.println(map.get("final"));
        String s1 = "小明";
        String s2 = "小" + "明";
        System.out.println(s1 == s2);    //true

        String str1 = "小";
        String str2 = "明";
        String s3 = str1 + str2;
        System.out.println(s1 == s3);        //false

        //宏替换
        final String str3 = "小";
        final String str4 = "明";
        String s4 = str3 + str4;
        System.out.println(s1 == s4);

        Integer a = 120;
        Integer b = 120;
        System.out.println(a==b);
    }

    public static Map<String, String> changeValue(final Map<String, String> map) {
        map.put("final", "alreadyChanged");
        return map;
    }

    public static Map<String, String> change(Map<String, String> map) {
        //改变了引用地址所指向的内容
        map.put("final", "alreadyChanged");
        //改变了map的引用  指向了一个新的对象
        map = new HashMap<String, String>();
        map.put("final", "newMap");
        return map;
    }

    private static StringBuilder testFinal(final StringBuilder builder) {
        builder.append(" Final");
        return builder;
    }

    public static void changInt(Integer a){
        //这里相当与创建了一个新的对象
        a = 6;
    }
}

