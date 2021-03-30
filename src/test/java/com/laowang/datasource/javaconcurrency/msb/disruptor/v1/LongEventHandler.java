package com.laowang.datasource.javaconcurrency.msb.disruptor.v1;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {

    /**
     * 这个才是消费者的方法
     * @param event
     * @param sequence
     * @param endOfBatch
     * @throws Exception
     */
    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
    System.out.println("值是: "+event.getValue()+" 顺序是: "+sequence);
    }
}
