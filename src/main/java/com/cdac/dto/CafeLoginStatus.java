package com.cdac.dto;

public class CafeLoginStatus extends Status{

	private long cafeId;
	private String name;
	private String role;
	
	
	public long getCafeId() {
		return cafeId;
	}
	public void setCafeId(Long long1) {
		this.cafeId = long1;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	/*private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}*/
}