package com.example.model;

import java.math.BigDecimal;
import java.util.Map;

public class Cart {
	private Map<Product, Integer> list;
	private String user;
	private String status;
	private BigDecimal amount;
	
	public Map<Product, Integer> getList() {
		return list;
	}
	public void setList(Map<Product, Integer> list) {
		this.list = list;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Cart other = (Cart) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Cart [" + list + ", " + user + ", " + status + "]";
	}
	
	public Cart(Map<Product, Integer> list, String user, String status, BigDecimal amount) {
		super();
		this.list = list;
		this.user = user;
		this.status = status;
		this.amount = amount;
	}
	
	public BigDecimal getTotal(){
		BigDecimal total = new BigDecimal("0.00");
		for(Product pro: list.keySet()){
			total.add(pro.getPrice().multiply(new BigDecimal(list.get(pro))));
		}
		amount = total;
		return total;
	}
	public BigDecimal getAmount() {
		getTotal();
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
