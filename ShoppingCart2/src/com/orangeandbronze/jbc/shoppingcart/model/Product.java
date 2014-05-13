package com.orangeandbronze.jbc.shoppingcart.model;

import java.math.BigDecimal;


public class Product {
	private String name;
	private int productNo;
	private String info;
	private BigDecimal price;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + productNo;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (productNo != other.productNo)
			return false;
		return true;
	}
	public Product(String name, int productNo, String info, BigDecimal price) {
		super();
		this.name = name;
		this.productNo = productNo;
		this.info = info;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public int getProductNo() {
		return productNo;
	}
	public String getInfo() {
		return info;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return productNo + " " + name + " " + price.toPlainString() + " " + info; 
	}
}
