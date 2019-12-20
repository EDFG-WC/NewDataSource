package com.laowang.datasource.javaconcurrency.phase2.chapter15;

import lombok.Data;

@Data
public class Message {

  private final String value;

  public Message(String value) {
    this.value = value;
  }
}
