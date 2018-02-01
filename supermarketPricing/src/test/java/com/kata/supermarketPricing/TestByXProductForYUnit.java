package com.kata.supermarketPricing;

import static org.junit.Assert.assertEquals;

import java.util.Currency;
import java.util.Locale;

import org.junit.Test;

import com.kata.supermarketPricing.entities.Product;
import com.kata.supermarketPricing.entities.Stock;
import com.kata.supermarketPricing.entities.management.ProductManager;
import com.kata.supermarketPricing.service.XForYPricingStrategy;
import com.kata.supermarketPricing.utils.Units;

/**
 * This class tests product pricing based on formula X for Y Unit e.g
 * 3 for 1 Unit e.g 3 coca for the price of one
 */
public class TestByXProductForYUnit {

	ProductManager productManager = new ProductManager();
	// Setting the product currency based on local country
	Currency curr = Currency.getInstance(Locale.FRANCE);
	// Offer creation
	XForYPricingStrategy offer = new XForYPricingStrategy(3, 1);
	// Product création
	Product p1 = new Product(Stock.getProductReference("Chocolate"), "Chocolate", Units.PIECE, 20, 0.5, curr);

	/**
	 * 3 for 1 Pricing e.g 3 coca for the price of one so how much does 6 coca
	 * costs? Tests the price of 6 products units (X multiple of the quantity in
	 * offer)
	 */
	@Test
	public void calculatePriceXProductForYUnitWhenXIsMultipleOfOfferQt() {
		productManager.addProduct(p1);
		// Test the price of 6 products units
		String newPrice = offer.getXProductForYUnit(p1.getUnitPrice(), 6, offer.getUnitScale(),
				offer.getQuantityScale(), curr, p1.getProductUnit());
		System.out.println(newPrice);
		assertEquals("2" + p1.getProductUnit(), newPrice);

	}

	/**
	 * 3 for 1 Pricing e.g 3 coca for the price of one so how much does 2 coca
	 * costs? Tests the price of 2 products units (X is smaller than the quantity in
	 * offer)
	 */
	@Test
	public void calculatePriceXProductForYUnitWhenXIsSmallerThenTheOfferQt() {
		productManager.addProduct(p1);
		// Test the price of 2 products units
		String newPrice = offer.getXProductForYUnit(p1.getUnitPrice(), 2, offer.getUnitScale(),
				offer.getQuantityScale(), curr, p1.getProductUnit());
		System.out.println(newPrice);
		assertEquals("1.0" + curr, newPrice);

	}

	/**
	 * 3 for 1 Pricing e.g 3 coca for the price of one so how much does 8 coca
	 * costs? Tests the price of 8 products units (X is bigger than the quantity in
	 * offer)
	 */
	@Test
	public void calculatePriceXProductForYUnitWhenXIsBiggerThenTheOfferQt() {
		productManager.addProduct(p1);
		String newPrice = offer.getXProductForYUnit(p1.getUnitPrice(), 8, offer.getUnitScale(),
				offer.getQuantityScale(), curr, p1.getProductUnit());
		System.out.println(newPrice);
		assertEquals("2" + p1.getProductUnit() + " And 1.0" + curr, newPrice);

	}

}
