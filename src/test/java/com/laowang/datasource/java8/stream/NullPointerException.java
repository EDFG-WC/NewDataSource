package com.laowang.datasource.java8.stream;

public class NullPointerException {

  public static void main(String[] args) {
    // 显而易见的空指针异常:
//    String insuranceName = getInsuranceName(new Person());
//    System.out.println(insuranceName);
    String insuranceNameByDeepDoubts = getInsuranceNameByDeepDoubts(new Person());
    System.out.println(insuranceNameByDeepDoubts);
    String insuranceNameByMultiExit = getInsuranceNameByMultiExit(new Person());
    System.out.println(insuranceNameByMultiExit);
  }

  private static String getInsuranceName(Person person) {
    return person.getCar().getInsurance().getName();
  }

  private static String getInsuranceNameByDeepDoubts(Person person) {
    if (null != person) {
      Car car = person.getCar();
      if (null != car) {
        Insurance insurance = car.getInsurance();
        if (null != insurance) {
          return insurance.getName();
        }
      }
    }
    return "UNKNOWN";
  }

  private static String getInsuranceNameByMultiExit(Person person) {
    final String defaultValue = "UNKNOWN";

    if (null == person) {
      return defaultValue;
    }
    Car car = person.getCar();
    if (null == car) {
      return defaultValue;
    }

    Insurance insurance = car.getInsurance();
    if (null == insurance) {
      return defaultValue;
    }

    return insurance.getName();
  }
}
