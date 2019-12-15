package com.laowang.datasource.javaconcurrency.phase2.chapter12;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class BalkingData {

  private final String fileName;
  private String content;
  private boolean isChanged;

  public BalkingData(String fileName, String content) {
    this.fileName = fileName; //保存的文件名
    this.content = content; // 保存的内容
    this.isChanged = true; //内容修改但没有保存则为true
  }

  /**
   * change和save必须成对出现
   *
   * @param newContent
   */
  public synchronized void change(String newContent) {
    this.content = newContent;
    this.isChanged = true;
  }

  /**
   * 内容若有修改, 则进行保存
   * @throws IOException
   */
  public synchronized void save() throws IOException {
    if (!isChanged) {
      return;
    }
    dosave();
    this.isChanged = false;
  }

  /**
   *
   * @throws IOException
   */
  private void dosave() throws IOException {
    System.out.println(Thread.currentThread().getName() + " calls do save, content= " + content);
    try (Writer writer = new FileWriter(fileName, true)) {
      writer.write(content);
      writer.write(System.lineSeparator());
      writer.flush();
    }
  }
}
