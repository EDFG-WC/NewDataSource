package com.laowang.datasource.guava.eventbus.eventBusSourceCode;

/**
 * 我们定义的事件总线
 */
public class MyEventBus implements Bus {
    private final static String DEFAULT_BUS_NAME = "default";
    private final static String DEFAULT_TOPIC = "default-topic";
    private final MyRegistry registry = new MyRegistry();
    private final MyEventExceptionHandler exceptionHandler;
    private final String busName;

    public MyEventBus() {
        this(DEFAULT_BUS_NAME,null);
    }

    public MyEventBus(String busName, MyEventExceptionHandler exceptionHandler) {
        this.busName = busName;
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public void register(Object subscriber) {

    }

    @Override
    public void unregister(Object subscriber) {

    }

    @Override
    public void post(Object event) {
        this.post(event, DEFAULT_TOPIC);
    }

    @Override
    public void post(Object event, String topic) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getBusName() {
        return busName;
    }
}
