package com.laowang.datasource.java8.stream;

import java.util.Optional;
import java.util.function.Supplier;

public class OptionalUsage {

  public static void main(String[] args) {
    //optional的三个方法: of, empty, ofNullable{of/empty}

    Optional<Insurance> insuranceOptional1 = Optional.<Insurance>empty();
    // get方法
//    insuranceOptional1.get();

    Optional<Insurance> insuranceOptional2 = Optional.of(new Insurance());
    insuranceOptional2.get();

    // 前2个方法的综合
    Optional<Insurance> insuranceOptional3 = Optional.ofNullable(null);

    // 如果insuranceOptional3为null, 就创建一个insurance的新对象返回, 参数类型是Supplier.
    Supplier<Insurance> insuranceSupplier = Insurance::new;
    Insurance insurance = insuranceSupplier.get();
    insurance.setName("假保险");
    // 这里是绝没办法给insuranceSupplier设置name的....每次只要用这个supplier它肯定就是空的
    insuranceOptional3.orElseGet(insuranceSupplier);

    // 方法引用不给力, 要想创建有内容的对象, 还得用这个: 有参构造+λ
    Insurance trueInsurance = new Insurance("真保险");
    insuranceOptional3.orElseGet(() -> trueInsurance);

    // 跟上面的区别在于orElse的参数是泛型, 必须要new
    insuranceOptional3.orElse(new Insurance());

    // 抛异常
//    insuranceOptional3.orElseThrow(RuntimeException::new);
//    insuranceOptional3.orElseThrow(IOException::new);
//    insuranceOptional3.orElseThrow(()->new RuntimeException("Not hava reference."));

//    Insurance insurance1 = insuranceOptional2.filter(i -> i.getName() == null).get();
    // NoSuchElementException: No value present
//    Insurance insurance1 = insuranceOptional2.filter(i -> i.getName() != null).get();
//    System.out.println(insurance1);

    Optional<String> nameOpt = insuranceOptional2.map(i -> i.getName());
    System.out.println(nameOpt.orElse("empty value"));
    System.out.println(nameOpt.isPresent());
    nameOpt.ifPresent(System.out::println);


  }

  private static String getInsuranceName(Insurance insurance) {
    if (null == insurance) {
      return "unknown";
    }
    return insurance.getName();
  }


  private static String getInsuranceNameByOpt(Insurance insurance) {
    //把对象包装成Optional, 返回类型最好不要是Optional
    return Optional.ofNullable(insurance).map(Insurance::getName).orElse("unknown");
  }
}
