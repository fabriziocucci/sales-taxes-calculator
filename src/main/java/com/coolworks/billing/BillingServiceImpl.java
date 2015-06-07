package com.coolworks.billing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.coolworks.cart.Cart;
import com.coolworks.cart.Item;
import com.coolworks.receipt.Purchase;
import com.coolworks.receipt.Receipt;

class BillingServiceImpl implements BillingService {

	private final TaxCalculationStrategy taxCalculationStrategy;
	
	@Inject
	public BillingServiceImpl(TaxCalculationStrategy taxCalculationStrategy) {
		this.taxCalculationStrategy = taxCalculationStrategy;
	}

	@Override
	public Receipt buyItems(Cart cart) {
		Collection<Purchase> purchases = cart.getItems().stream().map(this::buyItem).collect(Collectors.toCollection(ArrayList::new));
		BigDecimal taxes = purchases.stream().map(purchase -> purchase.getTaxes()).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal total = purchases.stream().map(purchase -> purchase.getTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
		return new Receipt(purchases, taxes, total);
	}
	
	private Purchase buyItem(Item item) {
		BigDecimal quantityAsBigDecimal = new BigDecimal(item.getQuantity());
		BigDecimal taxes = taxCalculationStrategy.calculateTaxes(item).multiply(quantityAsBigDecimal);
		BigDecimal total = item.getPrice().multiply(quantityAsBigDecimal).add(taxes);
		return new Purchase(item, taxes, total);
	}
	
}
