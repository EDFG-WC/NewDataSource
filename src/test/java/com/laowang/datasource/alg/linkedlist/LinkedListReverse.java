package com.laowang.datasource.alg.linkedlist;

import org.junit.Test;

public class LinkedListReverse {
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
    Node newHead = reverse1(head);
    System.out.println(newHead.getData());
  }

  public Node reverse1(Node head) {
    if (head == null || head.getNext() == null) {
      return head;
    }
    Node reHead = reverse1(head.getNext()); // 先反转后续节点head.getNext()
    head.getNext().setNext(head); // 将当前结点的指针域指向前一结点
    head.setNext(null); // 前一结点的指针域令为null;
    return reHead; // 反转后新链表的头结点
  }

  @Test
  public void test2() {
    Node node = reverse2(head);
    System.out.println(node.getData());
  }

  private Node reverse2(Node head) {
    if (head == null) {
      return head;
    }
    Node preNode = head;
    Node cur = head.getNext();
    Node temp;
    while(cur != null) {
      temp = cur.getNext();
      cur.setNext(preNode);

      preNode = cur;
      cur = temp;
    }
    head.setNext(null);
    return preNode;
  }
}
