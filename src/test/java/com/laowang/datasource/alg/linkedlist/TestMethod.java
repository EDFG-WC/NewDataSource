package com.laowang.datasource.alg.linkedlist;

import org.junit.Test;

public class TestMethod {
  Node head = new Node(0);
  Node node1 = new Node(1);
  Node node2 = new Node(2);
  Node node3 = new Node(3);

  {
    head.setNext(node1);
    node1.setNext(node2);
    node2.setNext(node3);
  }

  @Test
  public void test1() {
    Node newHead = loopSolution(head);
    //    Node newHead = recursionSolution(head);
    System.out.println(newHead);
  }

  private Node loopSolution(Node head) {
    if (head == null) {
      return head;
    }
    Node curr = head.getNext();
    // 头结点是不遍历的
    Node pre = head;
    Node temp;
    while (curr != null) {
      temp = curr.getNext();
      curr.setNext(pre);

      pre = curr;
      curr = temp;
    }
    head.setNext(null);
    return pre;
  }

  /**
   * 递归解决这个问题
   *
   * @param head 头
   * @return Node
   */
  private Node recursionSolution(Node head) {
    if (head == null || head.getNext() == null) {
      return head;
    }
    Node newHead = recursionSolution(head.getNext());
    head.getNext().setNext(head);
    head.setNext(null);
    return newHead;
  }
}
