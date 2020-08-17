//package com.laowang.datasource.guava.eventbus.eventBusSourceCode;
//
//import java.util.concurrent.Executor;
//import java.util.concurrent.ExecutorService;
//
///**
// * 用来分配处理任务, 里面会用到Executor的内容:
// */
//public class MyDispatcher {
//    private final Executor executorService;
//    private final MyEventExceptionHandler myEventExceptionHandler;
//    private final static Executor seq_executor_service = SeqExecutorService.INSTANCE;
//    private final static Executor per_thread_executor_service = PerThreadExecutorService.INSTANCE;
//
//    public void dispatch(Bus bus, MyRegistry registry, Object event, String topic) {
//    }
//
//    public void close() {
//        if (executorService instanceof ExecutorService) {
//            ((ExecutorService) executorService).shutdown();
//        }
//    }
//
//    static MyDispatcher newDispatcher(Executor executor, MyEventExceptionHandler exceptionHandler) {
//        return new MyDispatcher(executor, exceptionHandler);
//    }
//
//    static MyDispatcher seqDispatcher(MyEventExceptionHandler exceptionHandler) {
//        return new MyDispatcher(seq_executor_service, exceptionHandler);
//    }
//
//    static MyDispatcher seqDispatcher(MyEventExceptionHandler exceptionHandler) {
//        return new MyDispatcher(seq_executor_service, exceptionHandler);
//    }
//
//    private MyDispatcher(Executor executorService, MyEventExceptionHandler myEventExceptionHandler) {
//        this.executorService = executorService;
//        this.myEventExceptionHandler = myEventExceptionHandler;
//    }
//
//    private static class SeqExecutorService implements Executor {
//        private final static SeqExecutorService INSTANCE = new SeqExecutorService();
//
//        @Override
//        public void execute(Runnable command) {
//            command.run();
//        }
//    }
//
//    private static class PerThreadExecutorService implements Executor {
//        @Override
//        public void execute(Runnable command) {
//
//        }
//    }
//}
