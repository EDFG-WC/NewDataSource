package com.laowang.datasource.java8.stream;

/***************************************
 * @author:Alex Wang
 * @Date:2016/10/24 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class Insurance {

    private String name;

    public Insurance() {
    }

    public Insurance(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Insurance{" +
            "name='" + name + '\'' +
            '}';
    }
}
