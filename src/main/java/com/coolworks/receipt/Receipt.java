package com.coolworks.receipt;

import java.math.BigDecimal;
import java.util.Collection;

public class Receipt {

	private Collection<Purchase> purchases;
	private BigDecimal taxes;
	private BigDecimal total;
	
	@SuppressWarnings("unused")
	private Receipt() { }

	public Receipt(Collection<Purchase> purchases, BigDecimal taxes, BigDecimal total) {
		this.purchases = purchases;
		this.taxes = taxes;
		this.total = total;
	}

	public Collection<Purchase> getPurchases() {
		return purchases;
	}

	public BigDecimal getTaxes() {
		return taxes;
	}

	public BigDecimal getTotal() {
		return total;
	}

}
