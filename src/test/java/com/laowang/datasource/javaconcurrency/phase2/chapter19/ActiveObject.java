package com.laowang.datasource.javaconcurrency.phase2.chapter19;

/**
 * 接受异步消息的主动对象(ActiveObject), 它的每一个方法都有对应的实现类.
 */
public interface ActiveObject {
    // 直接调用的时候不能立即返回, 而是返回了一个future, 从future里拿到结果
    Result makeString(int count, char fillChar); // 转化成了MakeStringRequest

    void displayString(String text); // 转化成了DisplayStringRequest
}
