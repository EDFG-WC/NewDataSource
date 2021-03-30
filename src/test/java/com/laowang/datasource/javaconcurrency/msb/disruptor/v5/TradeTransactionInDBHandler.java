package com.laowang.datasource.javaconcurrency.msb.disruptor.v5;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class TradeTransactionInDBHandler implements WorkHandler<TradeTransaction>, EventHandler<TradeTransaction> {

    @Override
    public void onEvent(TradeTransaction tradeTransaction) throws Exception {
        // 这里做具体的消费逻辑
        // event.setId(UUID.randomUUID().toString());// 简单生成下ID
        // System.out.println(event.getId());
    }

    @Override
    public void onEvent(TradeTransaction tradeTransaction, long l, boolean b) throws Exception {
//        this.onEvent(event);
    }
}
