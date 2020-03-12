package com.laowang.datasource.guava.cache;

import com.google.common.base.MoreObjects;

public class Employee {

  private final String name;
  private final String dept;
  private final String empId;

  public Employee(String name, String dept, String empId) {
    this.name = name;
    this.dept = dept;
    this.empId = empId;
  }

  public String getName() {
    return name;
  }

  public String getDept() {
    return dept;
  }

  public String getEmpId() {
    return empId;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("Name", this.getName())
        .add("Department", this.getDept())
        .add("EmployeeID", this.getEmpId()).toString();
  }
}
