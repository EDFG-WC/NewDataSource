package com.laowang.datasource.guava.eventbus.eventBusSourceCode;

/**
 * 包含所有eventBus方法的接口:
 */
public interface Bus {
    // 注册
    void register(Object subscriber);
    // 注销
    void unregister(Object subscriber);

    void post(Object event);

    void post(Object event, String topic);
    // 关闭使用到的线程池线程:
    void close();

    String getBusName();
}
