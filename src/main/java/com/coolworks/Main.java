package com.coolworks;

import java.io.File;
import java.io.IOException;

import com.coolworks.billing.BillingModule;
import com.coolworks.billing.BillingService;
import com.coolworks.cart.Cart;
import com.coolworks.receipt.Receipt;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		if (args.length != 2) {
			throw new IllegalArgumentException("Expected an input file path for the shopping cart and an output file path for the receipt!");
		}
		
		Injector injector = Guice.createInjector(new BillingModule());
		ObjectMapper objectMapper = injector.getInstance(ObjectMapper.class);
		BillingService billingService = injector.getInstance(BillingService.class);
		
		File inputJsonFile = new File(args[0]);
		Cart cart = objectMapper.readValue(inputJsonFile, Cart.class);
		
		Receipt receipt = billingService.buyItems(cart);
		
		File outputJsonFile = new File(args[1]);
		objectMapper.writeValue(outputJsonFile, receipt);
		
	}
	
}
