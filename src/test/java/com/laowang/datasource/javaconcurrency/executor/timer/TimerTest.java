package com.laowang.datasource.javaconcurrency.executor.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
  private static final Timer timer = new Timer();
  // 内部类继承TimerTask
  static class MyTask extends TimerTask {
    @Override
    public void run() {
      System.out.println("MyTask just run!" + new Date());
    }
  }

  public static void main(String[] args) throws ParseException {
    MyTask myTask = new MyTask();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateStr = "2019-05-12 11:52:00";
    Date date = simpleDateFormat.parse(dateStr);
    System.out.println(
        "dateStr gives a time: " + date.toLocaleString() + " and current time is: " + new Date().toLocaleString());
    timer.schedule(myTask, date);
  }
}
