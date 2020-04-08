package com.laowang.classloader;

class Name{
    private String firstName;
    private String lastName;

    public Name() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Name(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
public class Person {
    private final Name name;
    public Person(Name name){
        //this.name = name;
        this.name = new Name(name.getFirstName(), name.getLastName());
    }
    public Name getName(){
        //return name;
        return new Name(name.getFirstName(), name.getLastName());
    }
    public static void main(String[] args) {
        Name n = new Name("明","小");
        Person p = new Person(n);
        System.out.println(p.getName().getFirstName());
        n.setFirstName("君");
        System.out.println(p.getName().getFirstName());
    }
}