package com.laowang.datasource.beans;

import java.util.Date;

/**
 * @Auther: wangc
 * @Date: 2018/10/28 20:55
 * @Description: 用于测试的商品类, 在页面显示.
 */
public class Product {

	private int id;

	private String name;

	private Double price;

	private Date createTime;

	private String remarks;

	public Product() {
		super();
	}

	public Product(int id, String name, Double price, Date createTime, String remarks) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.createTime = createTime;
		this.remarks = remarks;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
