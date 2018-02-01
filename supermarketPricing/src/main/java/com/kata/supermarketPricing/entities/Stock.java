package com.kata.supermarketPricing.entities;

import java.util.Map;
import java.util.TreeMap;

import com.kata.supermarketPricing.utils.Reference;

public class Stock {
	/** Instance unique du stock */
	private static Map<String, Product> myStock = new TreeMap<String, Product>();

	private Stock() {

	}

	/** Point d'accès pour l'instance unique du stock */
	public static Map<String, Product> getStock() {

		return myStock;
	}

	/** Point d'accès unique à une nouvelle réference d'un produit */
	public static String getProductReference(String productName) {

		return Reference.getCodeByProductName(productName);

	}

}
