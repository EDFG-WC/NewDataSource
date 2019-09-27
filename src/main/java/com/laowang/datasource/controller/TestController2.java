package com.laowang.datasource.controller;

import com.laowang.datasource.beans.Country;
import com.laowang.datasource.beans.Employees;
import com.laowang.datasource.beans.business.BasicInfoBean;
import com.laowang.datasource.mapper.CountryMapper;
import com.laowang.datasource.mapper.EmployeesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: wangc
 * @Date: 2018/10/21 22:11
 * @Description: 使用@Controller注解, 不会把返回值成json, 返回的hello会指向hello.html
 */
@RestController
public class TestController2 {
    @Autowired
    private EmployeesMapper employeesMapper;

    @Autowired
    private CountryMapper countryMapper;

    @RequestMapping("/hello")
    public String sayHello() {
//		model.addAttribute("name", "老王");
        return "hello";
    }

    @RequestMapping("/getOneEmp")
    public String getOneEmp() {
        Employees employees = employeesMapper.selectByPrimaryKey(10019);
        return employees.toString();
    }

    @RequestMapping("/getOneCountry")
    public String getOneCountry() {
        Country country = countryMapper.selectByPrimaryKey("ABW");
        return country.toString();
    }

    @RequestMapping("/getTest")
    public String test001() {
        BasicInfoBean bib = new BasicInfoBean();
        bib.setBaseName("world");
        bib.setDsName("ds2");
        bib.setPort(33062);
        bib.setPara2(10019);
        bib.setPara1("ZombieLand");
        Country country = countryMapper.selectByName(bib);
        return country.toString();
    }

    @RequestMapping("/getTest2")
    public String test002() {
        BasicInfoBean bib = new BasicInfoBean();
        bib.setBaseName("spring");
        bib.setDsName("ds1");
        bib.setPort(3306);
        bib.setPara2(10001);
        bib.setPara1("American Samoa");
        Employees employee = employeesMapper.selectByEmpNum(bib);
        return employee.toString();
    }
}
