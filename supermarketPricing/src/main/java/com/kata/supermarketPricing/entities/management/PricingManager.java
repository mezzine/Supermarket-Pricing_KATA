package com.kata.supermarketPricing.entities.management;

import java.util.Currency;

public class PricingManager {

	public String calculatePrice(int quantity, double unitPrice, Currency productCurrency) {
		return String.valueOf(quantity * unitPrice)+productCurrency;

	}

	protected boolean checkIfMultiple(double quantityToBuy, int quantityScale) {

		return quantityToBuy % quantityScale == 0;
	}


}
