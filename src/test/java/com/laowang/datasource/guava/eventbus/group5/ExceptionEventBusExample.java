package com.laowang.datasource.guava.eventbus.group5;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;

public class ExceptionEventBusExample {
    public static void main(String[] args) {
        final EventBus eventBus = new EventBus((exception, context) -> {
            System.out.println(context.getEvent());
            System.out.println(context.getEventBus());
            System.out.println(context.getSubscriber());
            System.out.println(context.getSubscriberMethod());
        });
        ExceptionListener exceptionListener = new ExceptionListener();
        eventBus.register(exceptionListener);
        eventBus.post("你就是个棒槌!");
    }

    // 我们可以在这个类中, 得到详细的错误信息:
//    static class ExceptionHandler implements SubscriberExceptionHandler {
//        @Override
//        public void handleException(Throwable throwable, SubscriberExceptionContext context) {
//            System.out.println(context.getEvent());
//            System.out.println(context.getEventBus());
//            System.out.println(context.getSubscriber());
//            System.out.println(context.getSubscriberMethod());
//        }
//    }
}
