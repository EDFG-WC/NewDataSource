package com.laowang.datasource.controller;

import org.springframework.stereotype.Component;

@Component // 同样这里是必须的
public class ConsumerMsg {
//    @KafkaListener(topics = "topic1")
//    public void listenMsg(ConsumerRecord<?, String> record) {
//        String value = record.value();
//        System.out.println("ConsumerMsg====>" + value);
//    }
}
