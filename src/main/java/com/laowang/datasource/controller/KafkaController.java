package com.laowang.datasource.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class KafkaController {
//    @Resource
//    private final KafkaTemplate<Object, Object> kafkaTemplate;
//
//    @GetMapping("/send/{message}")
//    public String send(@PathVariable String message) {
//        kafkaTemplate.send("topic1", "topci1:" + message);
//        kafkaTemplate.send("topic2", "topci2:" + message);
//        return message;
//    }
}
