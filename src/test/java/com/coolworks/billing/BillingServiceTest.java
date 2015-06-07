package com.coolworks.billing;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.coolworks.billing.BillingModule;
import com.coolworks.billing.BillingService;
import com.coolworks.cart.Cart;
import com.coolworks.receipt.Receipt;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class BillingServiceTest {

	private static final String PROVIDED_INPUT_1 = "src\\test\\resources\\input\\input1.json";
	private static final String PROVIDED_OUTPUT_1 = "src\\test\\resources\\output\\output1.json";
	
	private static final String PROVIDED_INPUT_2 = "src\\test\\resources\\input\\input2.json";
	private static final String PROVIDED_OUTPUT_2 = "src\\test\\resources\\output\\output2.json";
	
	private static final String PROVIDED_INPUT_3 = "src\\test\\resources\\input\\input3.json";
	private static final String PROVIDED_OUTPUT_3 = "src\\test\\resources\\output\\output3.json";
	
	private ObjectMapper objectMapper;
	private BillingService billingService;
	
	@Before
	public void initializeBillingService() throws IOException {
		Injector injector = Guice.createInjector(new BillingModule());
		this.objectMapper = injector.getInstance(ObjectMapper.class);
		this.billingService = injector.getInstance(BillingService.class);
	}
	
	@Test
	public void testProvidedInput1() throws JsonParseException, JsonMappingException, IOException {
		testProvidedInput(PROVIDED_INPUT_1, PROVIDED_OUTPUT_1);
	}

	@Test
	public void testProvidedInput2() throws JsonParseException, JsonMappingException, IOException {
		testProvidedInput(PROVIDED_INPUT_2, PROVIDED_OUTPUT_2);
	}
	
	@Test
	public void testProvidedInput3() throws JsonParseException, JsonMappingException, IOException {
		testProvidedInput(PROVIDED_INPUT_3, PROVIDED_OUTPUT_3);
	}
	
	private void testProvidedInput(String providedInputFile, String providedOutputFile) throws IOException, JsonParseException, JsonMappingException {
		
		Cart cart = objectMapper.readValue(new File(providedInputFile), Cart.class);
		
		Receipt receipt = billingService.buyItems(cart);
		JsonNode receiptAsTreeNode = objectMapper.valueToTree(receipt);
		
		Receipt expectedReceipt = objectMapper.readValue(new File(providedOutputFile), Receipt.class);
		JsonNode expectedReceiptAsTreeNode = objectMapper.valueToTree(expectedReceipt);
		
		assertEquals("The receipt does not match the expected one.", expectedReceiptAsTreeNode, receiptAsTreeNode);
		
	}

}
