package com.laowang.datasource.guava.eventbus.eventBusSourceCode;

/**
 * 用于处理event bus里的异常:
 */
public interface MyEventExceptionHandler {
    void handle(Throwable cause, MyEventContext context);
}
