package com.kata.supermarketPricing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Currency;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;

import com.kata.supermarketPricing.entities.Product;
import com.kata.supermarketPricing.entities.Stock;
import com.kata.supermarketPricing.entities.management.ProductManager;
import com.kata.supermarketPricing.utils.MyExceptions;
import com.kata.supermarketPricing.utils.Units;

/**
 * Unit test for the stock management.
 */
public class TestProductManagement {
	static Map<String, Product> myStock = Stock.getStock();
	ProductManager productManager = new ProductManager();

	// Setting the product currency based on local country
	Currency curr = Currency.getInstance(Locale.FRANCE);

	Product p1 = new Product(Stock.getProductReference("Chocolate"), "Chocolate", Units.PIECE, 20, 0.5, curr);
	Product p2 = new Product(Stock.getProductReference("Pepsi"), "Pepsi", Units.PIECE, 20, 1, curr);
	Product p3 = new Product(Stock.getProductReference("Banane"), "Banane", Units.KG, 10, 4, curr);
	Product p4 = new Product(Stock.getProductReference("Strawberry"), "Strawberry", Units.KG, 10, 5, curr);

	/**
	 * Fullfill the stock and test if it contains elements
	 */
	@Test
	public void testFillInTheStock() {

		productManager.addProduct(p1);
		productManager.addProduct(p2);
		productManager.addProduct(p3);
		productManager.addProduct(p4);

		assertNotNull(myStock);
		System.out.println(Stock.getStock().toString());

	}

	/**
	 * Test product exsistance by product name
	 * 
	 * @throws MyExceptions
	 */
	@Test
	public void testProductExsistance() throws MyExceptions {
		int quantity = 5;
		assertNotNull(productManager.checkProductExsistingQTE(p2.getProductRef(), quantity));
	}

	/**
	 * Test throwing MYException when quantity introduced is bigger than the product
	 * quantity in stock
	 * 
	 * @throws MyExceptions
	 */

	@Test(expected = MyExceptions.class)
	public void testInsufficientProductQuantityException() throws MyExceptions {
		int quantity = 30;
		productManager.checkProductExsistingQTE(p2.getProductRef(), quantity);
	}

	/**
	 * Test product exsistance by product reference
	 * 
	 * @throws MyExceptions
	 */
	@Test
	public void testAddExsistingProduct() throws MyExceptions {
		productManager.addProduct(p1);
		productManager.addProduct(p2);
		productManager.addProduct(p3);
		productManager.addProduct(p4);
		Product p = new Product(Stock.getProductReference("Chocolate"), "Chocolate", Units.PIECE, 15, 0.5, curr);
		if (productManager.checkProductExsistance(p.getProductRef())) {
			int updatedProductQt = productManager.updateExsistingProduct(p);
			assertEquals(35, updatedProductQt);
		}
	}

	/**
	 * Test product removal 
	 * 
	 * @throws MyExceptions
	 */
	@Test
	public void testRemoveProductByRefFromStock() throws MyExceptions {
		productManager.addProduct(p1);
		productManager.addProduct(p2);
		productManager.addProduct(p3);
		productManager.addProduct(p4);

		int StockEltBefore = Stock.getStock().size();
		String toBeRemovedProd = "CH";
		if (productManager.checkProductExsistance(toBeRemovedProd)) {
			productManager.deleteExsistingProduct(toBeRemovedProd);
		}
		int StockEltAfter = Stock.getStock().size();
		System.out.println("My stock after deleting product with ref=" + toBeRemovedProd + Stock.getStock().toString());
		assertNotEquals(StockEltBefore, StockEltAfter);

	}

}
