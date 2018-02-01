package com.kata.supermarketPricing.service;

import com.kata.supermarketPricing.entities.management.PricingManager;

/**
 * 2 for 1 Pricing e.g discount 2 for 1$
 */
public class XForFixedPricingStrategy extends PricingManager {

	double priceScale;
	int quantityScale;

	public XForFixedPricingStrategy(int quantityScale,double priceScale ) {
		super();
		this.priceScale = priceScale;
		this.quantityScale = quantityScale;
	}

	public double getXProductForFixedPrice(Double initialPrice, int quantity) {

		if (checkIfMultiple(quantity, quantityScale))
			return extractDiscountedQt(quantity);
		else if ((quantity < quantityScale))
			return quantity * initialPrice;
		else if (quantity > quantityScale)
			return extractDiscountedQt(quantity)
					+ (quantity - (quantity / quantityScale) * quantityScale) * initialPrice;
		return 0;
	}

	private double extractDiscountedQt(int quantity) {
		return (quantity / quantityScale) * priceScale;
	}

	public double calculateDiscount(Double initialePrice, int quantity) {

		return (quantity < quantityScale)?(initialePrice * quantity) - getXProductForFixedPrice(initialePrice, quantity): 0;

	}

}
