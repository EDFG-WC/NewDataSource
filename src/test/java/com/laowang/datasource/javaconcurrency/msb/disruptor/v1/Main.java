package com.laowang.datasource.javaconcurrency.msb.disruptor.v1;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        //Executor executor = Executors.newCachedThreadPool();

        LongEventFactory factory = new LongEventFactory();

        //must be power of 2
        int ringBufferSize = 1024;
        /**
         * 参数：
         * 1. 产生参数的工厂
         * 2. buffer的大小
         * 3. 消费者使用线程的来源
         */
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory, ringBufferSize, Executors.defaultThreadFactory());
        // 连接消息的处理方(也就是消费者)
        disruptor.handleEventsWith(new LongEventHandler());
        // 启动
        disruptor.start();
        // 从disruptor中获取要发布的ringbuffer
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        // 把ringBuffer给生产者
        LongEventProducer producer = new LongEventProducer(ringBuffer);
        // nio类提供的对象, 给了8, 实际上后面只用到了1第一位
        ByteBuffer bb = ByteBuffer.allocate(8);

        for(long l = 0; l<100; l++) {
            // 每次都给第0个赋值l
            bb.putLong(0, l);
            // 具体事宜由producer操作
            producer.onData(bb);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 这里结束.
        disruptor.shutdown();
    }
}
