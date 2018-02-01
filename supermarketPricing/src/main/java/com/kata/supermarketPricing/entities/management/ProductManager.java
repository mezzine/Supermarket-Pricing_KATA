package com.kata.supermarketPricing.entities.management;

import java.util.Map;

import com.kata.supermarketPricing.entities.Product;
import com.kata.supermarketPricing.entities.Stock;
import com.kata.supermarketPricing.utils.MyExceptions;

public class ProductManager {

	public Product addProduct(Product p) {

		Stock.getStock().put(p.getProductRef(), p);
		return p;

	}

	public Product getProductByReference(String productRef) {
		return Stock.getStock().entrySet().stream().filter(s -> s.getKey().equals(productRef))
				.map(Map.Entry::getValue).findFirst().orElse(null);

	}

	public Boolean checkProductExsistance(String productRef) throws MyExceptions {
		if (Stock.getStock().entrySet().stream().anyMatch(x -> x.getKey().equals(productRef)
				))
			return true;
		else
			throw new MyExceptions(
					"The product you entered does not exsist!");
	}
	public Boolean checkProductExsistingQTE(String productRef, int quantity) throws MyExceptions {
		if (Stock.getStock().entrySet().stream().anyMatch(x -> x.getKey().equals(productRef)
				&& quantity <= x.getValue().getProductQtInStock()))
			return true;
		else
			throw new MyExceptions(
					"The quantity you entred for this product is bigger than valuable in stock ! ");
	}

	public int updateExsistingProduct(Product p1) {
		Product old = Stock.getStock().entrySet().stream()
				.filter(p -> p.getKey().equals(p1.getProductRef())).findFirst().get().getValue();

		old.setProductQtInStock(p1.getProductQtInStock() + old.getProductQtInStock());
		return old.getProductQtInStock();

	}

	public void deleteExsistingProduct(String toBeRemovedProd) {
		Stock.getStock().entrySet().removeIf(entry -> entry.getKey().equals(toBeRemovedProd));
		
	}

}
