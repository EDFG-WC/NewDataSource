package com.laowang.datasource.alg.mergelinkedlist;

import com.laowang.datasource.alg.linkedlist.Node;
import org.junit.Test;
// 输入：1->2->4, 1->3->4
// 输出：1->1->2->3->4->4
public class TestMethod {
  Node link1 = new Node(1);
  Node link2 = new Node(2);
  Node link3 = new Node(4);

  //  Node node1 = new Node(1);
  //  Node node2 = new Node(3);
  //  Node node3 = new Node(4);
  Node node1 = new Node(5);

  {
    link1.setNext(link2);
    link2.setNext(link3);

    //    node1.setNext(node2);
    //    node2.setNext(node3);
  }

  @Test
  public void test() {
    Node head = getNewLinkedList(link1, node1);
    System.out.println(head);
  }

  private Node getNewLinkedList(Node link1, Node node1) {
    if (link1 == null) {
      return node1;
    }
    if (node1 == null) {
      return link1;
    }
    Node newHead;
    // recursion:
    if (link1.getData() < node1.getData()) {
      newHead = link1;
      Node newNode = getNewLinkedList(link1.getNext(), node1);
      link1.setNext(newNode);
    } else {
      newHead = node1;
      Node newNode = getNewLinkedList(node1.getNext(),link1);
      node1.setNext(newNode);

    }
    return newHead;
  }
}
