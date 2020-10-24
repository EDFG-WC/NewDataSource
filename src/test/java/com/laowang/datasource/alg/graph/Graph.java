package com.laowang.datasource.alg.graph;

import java.util.LinkedList;
import java.util.Queue;

public class Graph {
  private int v; // 顶点的个数
  private LinkedList<Integer> adj[]; // 邻接表

  public Graph(int v) {
    this.v = v;
    adj = new LinkedList[v];
    for (int i = 0; i < v; ++i) {
      adj[i] = new LinkedList<>();
    }
  }

  public void addEdge(int s, int t) { // 无向图一条边存两次
    adj[s].add(t);
    adj[t].add(s);
  }

  public void bfs(int s, int t) {
    if (s == t) return;
    boolean[] visited = new boolean[v]; // 记录已经被访问的顶点, 用来避免被重复访问, true就是被访问过
    visited[s] = true; // 访问第一个顶点
    Queue<Integer> queue = new LinkedList<>(); // queue是一个队列，用来存储已经被访问、但相连的顶点还没有被访问的顶点
    queue.add(s); // 存下第一个顶点, 此时第一个顶点被访问, 但是跟它相邻的顶点没有
    // prev用来记录搜索路径
    int[] prev = new int[v];
    for (int i = 0; i < v; ++i) {
      prev[i] = -1;
    }
    while (queue.size() != 0) {
      int w = queue.poll();
      for (int i = 0; i < adj[w].size(); ++i) {
        int q = adj[w].get(i);
        if (!visited[q]) {
          prev[q] = w; // q的前驱节点是w, 1的前驱节点是0
          if (q == t) {
            print(prev, s, t);
            return;
          }
          visited[q] = true;
          queue.add(q);
        }
      }
    }
  }

  private void print(int[] prev, int s, int t) { // 递归打印 s->t 的路径
    if (prev[t] != -1 && t != s) {
      print(prev, s, prev[t]);
    }
    System.out.print(t + " ");
  }
}
