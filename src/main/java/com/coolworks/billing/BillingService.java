package com.coolworks.billing;

import com.coolworks.cart.Cart;
import com.coolworks.receipt.Receipt;

public interface BillingService {

	Receipt buyItems(Cart cart);

}