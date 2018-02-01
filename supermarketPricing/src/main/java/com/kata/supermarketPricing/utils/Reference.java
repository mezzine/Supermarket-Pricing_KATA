package com.kata.supermarketPricing.utils;

import java.util.HashMap;
import java.util.Map;

public enum Reference {

	Chocolate("CH"), Pepsi("PE"), Banane("BA"), Strawberry("ST");

	private final String code;
	private static Map<String, String> mMap;

	private Reference(String code) {
		this.code = code;

	}

	public static String getCodeByProductName(String productName) {
		if (mMap == null) {
			initializeMapping();
		}
		if (mMap.containsKey(productName)) {
			return mMap.get(productName);
		}
		return productName;

	}

	private static void initializeMapping() {
		mMap = new HashMap<String, String>();
		for (Reference s : Reference.values()) {
			mMap.put(s.name(),s.code);
		}
	}

}