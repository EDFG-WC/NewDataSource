package com.laowang.datasource.java8.stream;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class StreamInAction {

  public static void main(String[] args) {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = Arrays.asList(
        new Transaction(brian, 2011, 300),
        new Transaction(raoul, 2012, 1000),
        new Transaction(raoul, 2011, 400),
        new Transaction(mario, 2012, 710),
        new Transaction(mario, 2012, 700),
        new Transaction(alan, 2012, 950)
    );

    //1. Find all transactions in the year 2011 and sort them by value (small to high).
    // sort方法如果用方法引用的方式来写, 只能从小到大排序
    List<Transaction> collect = transactions.stream()
        .filter(transaction -> transaction.getYear() == 2011)
        .sorted((t1, t2) -> t1.getValue() - t2.getValue()).collect(toList());
    System.out.println(collect);

    //2.What are all the unique cities where the traders work?
    List<String> cities = transactions.stream()
        .map(transaction -> transaction.getTrader().getCity()).distinct().collect(toList());
    System.out.println(cities);

    //3.Find all traders from Cambridge and sort them by name.
    List<Trader> traders = transactions.stream()
        .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
        .map(transaction -> transaction.getTrader())
        .sorted(Comparator.comparing(Trader::getName)).collect(toList());
    System.out.println(traders);

    //4.Return a string of all traders’ names sorted alphabetically
    Optional<String> reduce = transactions.stream()
        .sorted(Comparator.comparing(t -> t.getTrader().getName()))
        .map(transaction -> transaction.getTrader().getName()).distinct()
        .reduce((t1, t2) -> t1 + " " + t2);
    reduce.ifPresent(System.out::println);

    //5. Are any traders based in Milan?
    Optional<Transaction> milan = transactions.stream()
        .filter(transaction -> transaction.getTrader().getCity().equals("Milan")).findAny();
    System.out.println(milan.isPresent());

    //6.Print all transactions’ values from the traders living in Cambridge.
    transactions.stream()
        .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
        .forEach(transaction -> System.out.println(transaction.getValue()));
    System.out.println("======");
    //7.What’s the highest value of all the transactions?
    int value = transactions.stream().max(Comparator.comparing(Transaction::getValue)).get()
        .getValue();
    System.out.println(value);

    //8.Find the transaction with the smallest value.
    Transaction transaction = transactions.stream().min(Comparator.comparing(Transaction::getValue))
        .get();
    System.out.println(transaction);
  }
}
