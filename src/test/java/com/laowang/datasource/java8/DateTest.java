package com.laowang.datasource.java8;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;

// 千言万语一句话 Java 之前那个日期API真的很糟糕
public class DateTest {

  public static void main(String[] args) throws InterruptedException {
    // 这个填格林威治时间的注意糟透了
    Date date = new Date(116, 2, 18);
    System.out.println(date);
    // 这个API是线程不安全的, 这么跑肯定出问题.
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    for (int i = 0; i < 30; i++) {
      new Thread(() -> {
        for (int x = 0; x < 100; x++) {
          Date parseDate = null;
          try {
            parseDate = sdf.parse("20160505");
          } catch (ParseException e) {
            e.printStackTrace();
          }
          System.out.println(parseDate);
        }
      }).start();
    }

    //        testLocalDate();
//        testLocalTime();
//        combineLocalDateAndTime();
//    testInstant();
//    testDuration();
//        testPeriod();
//        testDateFormat();
    testDateParse();
  }

  private static void testLocalDate() {
    LocalDate localDate = LocalDate.of(2020, 2, 4);
    System.out.println(localDate.getYear());
    System.out.println(localDate.getMonth());
    System.out.println(localDate.getMonthValue());
    System.out.println(localDate.getDayOfYear());
    System.out.println(localDate.getDayOfMonth());
    System.out.println(localDate.getDayOfWeek());
    localDate.get(ChronoField.DAY_OF_MONTH);
  }

  private static void testLocalTime() {
    LocalTime time = LocalTime.now();
    System.out.println(time.getHour());
    System.out.println(time.getMinute());
    System.out.println(time.getSecond());
  }

  private static void combineLocalDateAndTime() {
    LocalDate localDate = LocalDate.now();
    LocalTime localTime = LocalTime.now();

    LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
    System.out.println(localDateTime.toString());
    LocalDateTime now = LocalDateTime.now();
    System.out.println(now);
  }

  // 新起止时间
  private static void testInstant() throws InterruptedException {
    // 拿到当前的时间
    Instant start = Instant.now();
    Thread.sleep(1000L);
    Instant end = Instant.now();
    // 得到消耗的时间
    Duration duration = Duration.between(start, end);
    System.out.println(duration.toMillis());
  }

  private static void testDuration() {
    LocalTime time = LocalTime.now();
    // 减掉时间1小时
    LocalTime beforeTime = time.minusHours(1);
    // 后面的时间-前面的时间
    Duration duration = Duration.between(beforeTime, time);
    System.out.println(duration.toHours());
  }

  private static void testPeriod() {
    Period period = Period.between(LocalDate.of(2014, 1, 10), LocalDate.of(2016, 1, 10));
    System.out.println(period.getMonths());
    System.out.println(period.getDays());
    System.out.println(period.getYears());
    System.out.println(period);
  }

  private static void testDateFormat() {
    LocalDate localDate = LocalDate.now();
    String format1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
//        String format2 = localDate.format(DateTimeFormatter.ISO_LOCAL_TIME);
    System.out.println(format1);
//        System.out.println(format2);

    DateTimeFormatter mySelfFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String format = localDate.format(mySelfFormatter);
    System.out.println(format);
  }

  private static void testDateParse() {
    String date1 = "20161113";
    LocalDate localDate = LocalDate.parse(date1, DateTimeFormatter.BASIC_ISO_DATE);
    System.out.println(localDate);

    DateTimeFormatter mySelfFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String date2 = "2016-11-13";
    LocalDate localDate2 = LocalDate.parse(date2, mySelfFormatter);
    System.out.println(localDate2);
  }
}
