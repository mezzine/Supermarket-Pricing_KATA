package com.kata.supermarketPricing.entities;

import java.util.Currency;

import com.kata.supermarketPricing.utils.Units;

public class Product {
	private String productRef;
	private String productName;
	private Units productUnit;
	private Integer productQtInStock;
	private double unitPrice;
	private Currency productCurrency;

	public Product(String productRef, String productName, Units productUnit, Integer productQtInStock, double unitPrice,
			Currency productCurrency) {
		super();
		this.productRef = productRef;
		this.productName = productName;
		this.productUnit = productUnit;
		this.productQtInStock = productQtInStock;
		this.unitPrice = unitPrice;
		this.productCurrency = productCurrency;
	}

	/**
	 * @return the productRef
	 */
	public String getProductRef() {
		return productRef;
	}

	/**
	 * @param productRef
	 *            the productRef to set
	 */
	public void setProductRef(String productRef) {
		this.productRef = productRef;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productUnit
	 */
	public Units getProductUnit() {
		return productUnit;
	}

	/**
	 * @param productUnit
	 *            the productUnit to set
	 */
	public void setProductUnit(Units productUnit) {
		this.productUnit = productUnit;
	}

	/**
	 * @return the productQtInStock
	 */
	public Integer getProductQtInStock() {
		return productQtInStock;
	}

	/**
	 * @param productQtInStock
	 *            the productQtInStock to set
	 */
	public void setProductQtInStock(Integer productQtInStock) {
		this.productQtInStock = productQtInStock;
	}

	/**
	 * @return the unitPrice
	 */
	public double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice
	 *            the unitPrice to set
	 */
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 * @return the productCurrency
	 */
	public Currency getProductCurrency() {
		return productCurrency;
	}

	/**
	 * @param productCurrency
	 *            the productCurrency to set
	 */
	public void setProductCurrency(Currency productCurrency) {
		this.productCurrency = productCurrency;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [productId=" + productRef + ", productName=" + productName + ", productUnit=" + productUnit
				+ ", unitPrice=" + unitPrice + ", productQtInStock=" + productQtInStock + "]";
	}

}
