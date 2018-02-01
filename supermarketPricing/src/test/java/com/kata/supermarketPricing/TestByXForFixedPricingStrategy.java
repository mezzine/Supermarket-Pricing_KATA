package com.kata.supermarketPricing;

import static org.junit.Assert.assertEquals;

import java.util.Currency;
import java.util.Locale;

import org.junit.Test;

import com.kata.supermarketPricing.entities.Product;
import com.kata.supermarketPricing.entities.Stock;
import com.kata.supermarketPricing.entities.management.ProductManager;
import com.kata.supermarketPricing.service.XForFixedPricingStrategy;
import com.kata.supermarketPricing.utils.Units;

/**
 * This class tests product pricing based on formula X for Y Pricing e.g
 * discount 2 for 1$
 */

public class TestByXForFixedPricingStrategy {

	ProductManager productManager = new ProductManager();
	// Setting the product currency based on local country
	Currency curr = Currency.getInstance(Locale.FRANCE);
	// Offer creation
	XForFixedPricingStrategy offer = new XForFixedPricingStrategy(5, 2);
	// Product création
	Product p1 = new Product(Stock.getProductReference("Chocolate"), "Chocolate", Units.PIECE, 20, 0.5, curr);

	/**
	 * 5 for 2 EURO e.g 5 Chocolate for the price of two so how much does 4 costs?
	 * Tests the price of 5 products units (X smaller than the quantity in offer)
	 */

	@Test
	public void testXForFixedPricingStrategyWithQtSmallerThanOffer() {

		productManager.addProduct(p1);
		String newPrice = String.valueOf(offer.getXProductForFixedPrice(p1.getUnitPrice(), 4))
				+ p1.getProductCurrency();
		System.out.println(newPrice);
		assertEquals("2.0" + p1.getProductCurrency(), newPrice);

	}

	/**
	 * 5 for 2 EURO e.g 5 Chocolate for the price of two so how much does 7 costs?
	 * Tests the price of 7 products units (X Bigger than the quantity in offer)
	 */

	@Test
	public void testXForFixedPricingStrategyWithQtBiggerThanOffer() {

		productManager.addProduct(p1);
		String newPrice = String.valueOf(offer.getXProductForFixedPrice(p1.getUnitPrice(), 7))
				+ p1.getProductCurrency();
		System.out.println(newPrice);
		assertEquals("3.0" + p1.getProductCurrency(), newPrice);

	}

	/**
	 * 5 for 2 EURO e.g 5 Chocolate for the price of two so how much does 4 costs?
	 * Tests the price of 10 products units (X is multiple of the quantity in offer)
	 */

	@Test
	public void ttestXForFixedPricingStrategyWithQtMultipleOfTheOffer() {

		productManager.addProduct(p1);
		String newPrice = String.valueOf(offer.getXProductForFixedPrice(p1.getUnitPrice(), 10))
				+ p1.getProductCurrency();
		System.out.println(newPrice);
		assertEquals("4.0" + p1.getProductCurrency(), newPrice);

	}

	/**
	 * Tests complex product pricing based on introduced quantity and strategy
	 * 
	 * 
	 */
	@Test
	public void testXForFixedPricingStrategyDiscount() {
		XForFixedPricingStrategy offer = new XForFixedPricingStrategy(5, 2);
		double discount = offer.calculateDiscount(p1.getUnitPrice(), 14);
		assertEquals(0, discount, 0.001);
	}

}
