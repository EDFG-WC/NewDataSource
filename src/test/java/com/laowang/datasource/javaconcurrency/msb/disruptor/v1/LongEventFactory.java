package com.laowang.datasource.javaconcurrency.msb.disruptor.v1;

import com.lmax.disruptor.EventFactory;

public class LongEventFactory implements EventFactory<LongEvent> {

    public LongEvent newInstance() {
        return new LongEvent();
    }
}
