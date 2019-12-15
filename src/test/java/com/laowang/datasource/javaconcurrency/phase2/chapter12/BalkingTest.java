package com.laowang.datasource.javaconcurrency.phase2.chapter12;

public class BalkingTest {

  public static void main(String[] args) {
    BalkingData data = new BalkingData("dildo.txt", "I an your dad.");
    new AutosaverThread("Auto Save", data).start();
    new ManualsaverThread("Manual Save", data).start();
  }
}
