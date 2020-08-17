package com.laowang.datasource.alitest;

import java.util.Objects;

public class TestDO {
    private String name;
    private int age;
    private int ChineseScore;

    public TestDO(String name, int age, int chineseScore) {
        this.name = name;
        this.age = age;
        ChineseScore = chineseScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getChineseScore() {
        return ChineseScore;
    }

    public void setChineseScore(int chineseScore) {
        ChineseScore = chineseScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestDO testDO = (TestDO) o;
        return age == testDO.age &&
                ChineseScore == testDO.ChineseScore &&
                name.equals(testDO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, ChineseScore);
    }
}
