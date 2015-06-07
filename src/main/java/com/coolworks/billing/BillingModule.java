package com.coolworks.billing;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class BillingModule extends AbstractModule {
	
	@Override
	protected void configure() {
		bind(BillingService.class).to(BillingServiceImpl.class).in(Singleton.class);;
	}
	
	@Provides @Singleton
	public ObjectMapper provideObjectMapper() {
		return new ObjectMapper();
	}
	
	@Provides @Singleton
	public TaxCalculationStrategy provideTaxCalculationStrategy() {
		BigDecimal basicTaxes = new BigDecimal(10);
		BigDecimal importTaxes = new BigDecimal(5);
		return new TaxCalculationStrategyImpl(basicTaxes, importTaxes);
	}
	
}
