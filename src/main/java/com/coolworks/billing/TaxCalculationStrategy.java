package com.coolworks.billing;

import java.math.BigDecimal;

import com.coolworks.cart.Item;

interface TaxCalculationStrategy {

	BigDecimal calculateTaxes(Item item);

}