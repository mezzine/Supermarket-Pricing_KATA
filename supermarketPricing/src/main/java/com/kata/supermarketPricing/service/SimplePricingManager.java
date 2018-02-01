package com.kata.supermarketPricing.service;

import com.kata.supermarketPricing.entities.Product;
import com.kata.supermarketPricing.entities.management.PricingManager;

public class SimplePricingManager extends PricingManager {

	public String calculateProductSimplePrice(int quantity, Product product ) {
		return calculatePrice(quantity,product.getUnitPrice(), product.getProductCurrency());
	}

}
