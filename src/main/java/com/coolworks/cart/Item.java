package com.coolworks.cart;

import java.math.BigDecimal;

public class Item {
	
	public enum Type {
		BOOK, FOOD, MEDICAL, OTHER;
	}
	
	private String name;
	private Type type;
	private int quantity;
	private BigDecimal price;
	private boolean imported;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public boolean isImported() {
		return imported;
	}
	
}