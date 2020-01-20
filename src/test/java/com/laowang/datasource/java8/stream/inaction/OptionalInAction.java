package com.laowang.datasource.java8.stream.inaction;

import java.util.Optional;

public class OptionalInAction {

  public static void main(String[] args) {
    String insuranceNameByOptional = getInsuranceNameByOptional(null);
    System.out.println(insuranceNameByOptional);
    // 或者再复杂点:
    Optional.ofNullable(insuranceNameByOptional).ifPresent(System.out::println);
  }
  // 这里使用的person, car都把成员变量用optional包裹了一次的
  private static String getInsuranceNameByOptional(Person person) {
    // map本身就会封装一次Optional, 而Person的成员变量car本身就有一个Optional, 所以会变成Optional<Optional<Car>>
    Optional<Optional<Car>> carOptional = Optional.ofNullable(person).map(Person::getCar);
    // 解决这个问题, 我们要用flatMap!
    String insuranceName = Optional.ofNullable(person).flatMap(p -> p.getCar())
        .flatMap(car -> car.getInsurance())
        .map(insurance -> insurance.getName()).orElse("unknown");
    return insuranceName;
  }
}
