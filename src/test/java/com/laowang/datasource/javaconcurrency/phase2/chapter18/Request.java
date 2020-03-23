package com.laowang.datasource.javaconcurrency.phase2.chapter18;

/**
 * 模拟的是要运输到传送带上的半成品
 */
public class Request {
    private final String name;
    private final int number;

    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    /**
     * 模拟加工产品的功能
     */
    public void execute() {
        System.out.println(Thread.currentThread().getName() + "executed " + this);
    }

    @Override public String toString() {
        return "Request{" + "name='" + name + '\'' + ", number=" + number + '}';
    }
}
