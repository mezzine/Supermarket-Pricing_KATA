package com.kata.supermarketPricing.service;

import java.util.Currency;

import com.kata.supermarketPricing.entities.management.PricingManager;
import com.kata.supermarketPricing.utils.Units;

/**
 * buy two, get one free e.g 3 pepsi for the price of two
 */
public class XGetYpricingStrategy extends PricingManager {
	int quantityScale;
	int unitScale;

	public XGetYpricingStrategy(int quantityScale, int unitScale) {
		super();
		this.unitScale = unitScale;
		this.quantityScale = quantityScale;
	}

	/**
	 * @return the quantityScale
	 */
	public int getQuantityScale() {
		return quantityScale;
	}

	/**
	 * @param quantityScale
	 *            the quantityScale to set
	 */
	public void setQuantityScale(int quantityScale) {
		this.quantityScale = quantityScale;
	}

	/**
	 * @return the unitScale
	 */
	public int getUnitScale() {
		return unitScale;
	}

	/**
	 * @param unitScale
	 *            the unitScale to set
	 */
	public void setUnitScale(int unitScale) {
		this.unitScale = unitScale;
	}

	public String calculateByXgetYproductUnit(double initialPrice, double quantityToBuy, int unitScale,
			int offerQtScale, Units productUnit, Currency productCurrency) {

		if (checkIfMultiple(quantityToBuy, offerQtScale))
			return String.valueOf(extractDiscountedQtPrice(quantityToBuy, productUnit)) + productUnit;

		else if (quantityToBuy < offerQtScale)
			return String.valueOf(quantityToBuy * initialPrice) + productCurrency;

		else if (quantityToBuy > offerQtScale)
			return String
					.valueOf(extractDiscountedQtPrice(quantityToBuy, productUnit) + (int)(quantityToBuy % quantityScale))
					+ productUnit;

		return null;

	}

	private int extractDiscountedQtPrice(double quantityToBuy, Units productUnit) {
		return  (int)(quantityToBuy / quantityScale) * getByXOfferQT();

	}

	private int getByXOfferQT() {
		return this.quantityScale + this.unitScale;

	}

}
