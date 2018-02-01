package com.kata.supermarketPricing.service;

import java.util.Currency;

import com.kata.supermarketPricing.entities.management.PricingManager;
import com.kata.supermarketPricing.utils.Units;

/**
 * 3 for 1 Pricing e.g 3 coca for the price of one
 */
public class XForYPricingStrategy extends PricingManager {
	
	int quantityScale;
	int unitScale;

	public XForYPricingStrategy(int quantityScale,int unitScale) {
		super();
		this.unitScale = unitScale;
		this.quantityScale = quantityScale;
	}

	public String getXProductForYUnit(Double initialPrice, int quantityToBuy, int offerUnitScale,
			int offerQtScale, Currency productCurrency, Units productUnit) {

		if (checkIfMultiple(quantityToBuy, offerQtScale))
			return extractDiscountedQtPrice(quantityToBuy, productUnit);
		else if (quantityToBuy < offerQtScale)
			return String.valueOf(quantityToBuy * initialPrice) + productCurrency;
		else if (quantityToBuy > offerQtScale)
			return extractDiscountedQtPrice(quantityToBuy, productUnit) + " And "
					+ remainingQtwithoutDiscount(quantityToBuy, offerQtScale) * initialPrice + productCurrency;
		return null;
	}

	private int remainingQtwithoutDiscount(int introducedQuantity, int quantityScale) {
		return introducedQuantity - (introducedQuantity / quantityScale) * quantityScale;
	}

	private String extractDiscountedQtPrice(int introducedQuantity, Units productUnit) {
		return String.valueOf((introducedQuantity / quantityScale) * unitScale) + productUnit;
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

}
