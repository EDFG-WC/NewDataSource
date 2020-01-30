 package com.laowang.datasource.java8;

// 如果对象是静态的, 那么就可以用这样的方式引入.

 import static com.laowang.datasource.java8.CollectorsAction.menu;

 import com.laowang.datasource.java8.stream.Dish;
 import java.util.Comparator;
 import java.util.List;
 import java.util.Map;
 import java.util.Optional;
 import java.util.function.BinaryOperator;
 import java.util.stream.Collectors;


 public class CollectorsAction3 {

   public static void main(String[] args) {
     testPartitioningByWithPredicate();
     testPartitioningByWithPredicateAndCollector();
     testReducingBinaryOperator();
     testReducingBinaryOperatorAndIdentity();
     testReducingBinaryOperatorAndIdentityAndFunction();
   }

   private static void testPartitioningByWithPredicate() {
     System.out.println("testPartitioningByWithPredicate");
     Map<Boolean, List<Dish>> collect = menu.stream()
         .collect(Collectors.partitioningBy(Dish::isVegetarian));
     Optional.ofNullable(collect).ifPresent(System.out::println);
   }

   private static void testPartitioningByWithPredicateAndCollector() {
     System.out.println("testPartitioningByWithPredicateAndCollector");
     Map<Boolean, Double> collect = menu.stream().collect(
         Collectors.partitioningBy(Dish::isVegetarian, Collectors.averagingInt(Dish::getCalories)));
     Optional.ofNullable(collect).ifPresent(System.out::println);
   }

   private static void testReducingBinaryOperator() {
     System.out.println("testReducingBinaryOperator");
     Optional<Dish> collect = menu.stream().collect(
         Collectors.reducing(BinaryOperator.maxBy(Comparator.comparingInt(Dish::getCalories))));
     collect.ifPresent(System.out::println);
   }

   private static void testReducingBinaryOperatorAndIdentity(){
     System.out.println("testReducingBinaryOperatorAndIdentity");
     Integer collect = menu.stream().map(Dish::getCalories)
         .collect(Collectors.reducing(0, (d1, d2) -> d1 + d2));
     System.out.println(collect);
   }

   private static void testReducingBinaryOperatorAndIdentityAndFunction() {
     System.out.println("testReducingBinaryOperatorAndIdentiyAndFunction");
     Integer result = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (d1, d2) -> d1 + d2));
     System.out.println(result);
   }
 }
