package com.kata.supermarketPricing;

import static org.junit.Assert.assertEquals;

import java.util.Currency;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;

import com.kata.supermarketPricing.entities.Product;
import com.kata.supermarketPricing.entities.Stock;
import com.kata.supermarketPricing.entities.management.ProductManager;
import com.kata.supermarketPricing.service.SimplePricingManager;
import com.kata.supermarketPricing.utils.MyExceptions;
import com.kata.supermarketPricing.utils.Units;

public class TestSimpleProductPricing {
	/**
	 * Tests simple product pricing based on introduced quantity
	 * 
	 * @throws MyExceptions
	 */

	ProductManager productManager = new ProductManager();
	SimplePricingManager simplePricingManager = new SimplePricingManager();
	// Setting the product currency based on local country
	Currency curr = Currency.getInstance(Locale.FRANCE);

	Product p1 = new Product(Stock.getProductReference("Chocolate"), "Chocolate", Units.PIECE, 20, 0.5, curr);

	/*
	 * Tests simple product pricing based on introduced quantity eg:: by 5 Chocolate
	 * each unit costs 0.5EURO
	 * 
	 * @throws MyExceptions
	 */

	@Test
	public void testSimpleProductPricing() throws MyExceptions {
		String price = null;
		int quantity = 5;
		productManager.addProduct(p1);

		if (productManager.checkProductExsistingQTE(p1.getProductRef(), quantity)) {
			Product pr = productManager.getProductByReference(p1.getProductRef());
			price = simplePricingManager.calculateProductSimplePrice(quantity, pr);

		}
		System.out.println(price);
		assertEquals("2.5" + curr, price);

	}

}
