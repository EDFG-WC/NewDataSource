package com.laowang.datasource.javaconcurrency.phase2.chapter5;

/**
 * SharedResource
 */
public class Gate {

  private final Object LOCK = new Object();
  private int counter = 0;
  private String name = "Nobody";
  private String address = "Nowhere";

  /**
   * 临界值, 多线程一旦进入这个方法就会产生竞争
   *
   * @param name
   * @param address
   */
  public synchronized void pass(String name, String address) {
    this.counter++;
    this.name = name;
    this.address = address;
    verify();
  }

  private void verify() {
    if (this.name.charAt(0) != this.address.charAt(0)) {
      System.out.println("************BROKEN**************" + toString());
    }
  }

  @Override
  public synchronized String toString() {
    return "Gate{" +
        "counter=" + counter +
        ", name='" + name + '\'' +
        ", address='" + address + '\'' +
        '}';
  }
}
