package com.coolworks.billing;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.coolworks.cart.Item;

class TaxCalculationStrategyImpl implements TaxCalculationStrategy {

	private static final BigDecimal TWENTY = new BigDecimal(20);
	private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
	
	private final BigDecimal basicTaxes;
	private final BigDecimal importTaxes;
	
	public TaxCalculationStrategyImpl(BigDecimal basicTaxes, BigDecimal importTaxes) {
		this.basicTaxes = basicTaxes;
		this.importTaxes = importTaxes;
	}
	
	@Override
	public BigDecimal calculateTaxes(Item item) {
		
		BigDecimal taxes = BigDecimal.ZERO;
		
		if (item.getType().equals(Item.Type.OTHER)) {
			taxes = taxes.add(item.getPrice().multiply(basicTaxes).divide(ONE_HUNDRED));
		}
		
		if (item.isImported()) {
			taxes = taxes.add(item.getPrice().multiply(importTaxes).divide(ONE_HUNDRED));
		}
		
		return roundTaxes(taxes);
	}
	
	private BigDecimal roundTaxes(BigDecimal taxes) {
		return taxes.multiply(TWENTY).setScale(0, RoundingMode.UP).divide(TWENTY).setScale(2);
	}
	
}
