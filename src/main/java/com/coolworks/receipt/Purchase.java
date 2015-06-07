package com.coolworks.receipt;

import java.math.BigDecimal;

import com.coolworks.cart.Item;

public class Purchase {

	private Item item;
	private BigDecimal taxes;
	private BigDecimal total;
	
	@SuppressWarnings("unused")
	private Purchase() { }

	public Purchase(Item item, BigDecimal taxes, BigDecimal total) {
		this.item = item;
		this.total = total;
		this.taxes = taxes;
	}
	
	public Item getItem() {
		return item;
	}

	public BigDecimal getTaxes() {
		return taxes;
	}

	public BigDecimal getTotal() {
		return total;
	}

}
