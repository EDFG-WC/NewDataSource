package com.laowang.datasource.javaconcurrency.phase2.chapter5;

public class User extends Thread {

  private String myName;
  private String myAddress;
  private final Gate gate;

  public User(String myName, String myAddress,
      Gate gate) {
    this.myName = myName;
    this.myAddress = myAddress;
    this.gate = gate;
  }

  @Override
  public void run() {
    System.out.println(myName + " BEGIN");
    while (true) {
      this.gate.pass(myName, myAddress);
    }
  }
}
