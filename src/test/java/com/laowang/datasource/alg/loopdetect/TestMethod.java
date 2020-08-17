package com.laowang.datasource.alg.loopdetect;

import com.laowang.datasource.alg.linkedlist.Node;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

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
    node7.setNext(node3);
  }

  @Test
  public void test1() {
    boolean b = fastSlowIndex(head);
    System.out.println(b);
//    boolean b = setJudge(head);
//    System.out.println(b);
  }

  public boolean fastSlowIndex(Node head) {
    if (head == null) {
      return false;
    }
    Node pre = head;
    Node curr = head;
    while (curr.getNext() != null) {
      pre = pre.getNext();
      curr = curr.getNext().getNext();
      if (curr == null) {
        return false;
      } else if (pre == curr) {
        return true;
      }
    }
    return false;
  }

  /**
   * 用set来判断是否有环
   *
   * @param head
   * @return
   */
  public boolean setJudge(Node head) {
    Set<Node> set = new HashSet<>();
    while (null != head) {
      if (set.contains(head)) {
        return true;
      }
      set.add(head);
      head = head.getNext();
    }
    return false;
  }
}
