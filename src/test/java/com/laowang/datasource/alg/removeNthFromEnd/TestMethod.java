package com.laowang.datasource.alg.removeNthFromEnd;

import com.laowang.datasource.alg.linkedlist.Node;
import org.junit.Test;

public class TestMethod {
  Node head = new Node(0);
  Node node1 = new Node(1);
  Node node2 = new Node(2);
  Node node3 = new Node(3);
  Node node4 = new Node(4);
  Node node5 = new Node(5);
  Node node6 = new Node(6);
  Node node7 = new Node(7);

  {
    head.setNext(node1);
    node1.setNext(node2);
    node2.setNext(node3);
    node3.setNext(node4);
    node4.setNext(node5);
    node5.setNext(node6);
    node6.setNext(node7);
  }

  @Test
  public void test() {
    int length = getListLength(head);


  }

  private int getListLength(Node head) {
    int length = 0;
    while (head != null) {
      head = head.getNext();
      length++;
    }
    return length;
  }
}
