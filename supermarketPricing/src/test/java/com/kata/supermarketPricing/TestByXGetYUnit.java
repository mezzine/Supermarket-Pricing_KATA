package com.kata.supermarketPricing;

import static org.junit.Assert.assertEquals;

import java.util.Currency;
import java.util.Locale;

import org.junit.Test;

import com.kata.supermarketPricing.entities.Product;
import com.kata.supermarketPricing.entities.Stock;
import com.kata.supermarketPricing.entities.management.ProductManager;
import com.kata.supermarketPricing.utils.Units;
import com.kata.supermarketPricing.service.XGetYpricingStrategy;

/**
 * This class tests product pricing based on formula by X get Y units e.g:: buy
 * two, get one free
 */

public class TestByXGetYUnit {

	ProductManager productManager = new ProductManager();
	// Setting the product currency based on local country
	Currency curr = Currency.getInstance(Locale.FRANCE);
	// Offer creation
	XGetYpricingStrategy offer = new XGetYpricingStrategy(2, 1);
	// Product création
	Product p2 = new Product(Stock.getProductReference("Pepsi"), "Pepsi", Units.Bottle, 20, 2, curr);

	/**
	 * buy two, get one free e.g 3 pepsi for the price of two so how much does 1
	 * pepsi costs? Tests the price of 1 product unit (X is smaller than the
	 * quantity in offer)
	 */
	@Test
	public void testByXGetYUnitWhenXIsSmallerThanTheOfferQt() {

		String newPrice = offer.calculateByXgetYproductUnit(p2.getUnitPrice(), 1, offer.getUnitScale(),
				offer.getQuantityScale(), p2.getProductUnit(), p2.getProductCurrency());
		System.out.println(newPrice);
		assertEquals("2.0" + p2.getProductCurrency(), newPrice);

	}

	/**
	 * buy two, get one free e.g 3 pepsi for the price of two so how much does 4
	 * pepsi costs? Tests the price of 4 products units (X multiple of the quantity
	 * in offer)
	 */
	@Test
	public void testByXGetYUnitWhenXIsMultipleOfTheOfferQt() {

		String newPrice = offer.calculateByXgetYproductUnit(p2.getUnitPrice(), 4, offer.getUnitScale(),
				offer.getQuantityScale(), p2.getProductUnit(), p2.getProductCurrency());
		System.out.println(newPrice);
		assertEquals("6" + p2.getProductUnit(), newPrice);

	}

	/**
	 * buy two, get one free e.g 3 pepsi for the price of two so how many pepsi you
	 * get if you by 7 Tests the price of 7 products units (X is bigger than the
	 * quantity in offer)
	 */
	@Test
	public void testByXGetYUnitWhenXIsBiggerThanTheOfferQt() {

		String newPrice = offer.calculateByXgetYproductUnit(p2.getUnitPrice(), 7, offer.getUnitScale(),
				offer.getQuantityScale(), p2.getProductUnit(), p2.getProductCurrency());
		System.out.println(newPrice);
		assertEquals("10" + p2.getProductUnit(), newPrice);

	}

}