package com.jpm.assignment.pojo;

/**
 * Holds Stock information
 * 
 */
public class Stock {

	private String stockSymbol;
	
	private Double commonDividend;
	
	private Double preferredDividend;
	
	private Double parValue;
	
	
	public Stock(String stockSymbol,  Double commonDividend, Double preferredDividend, Double parValue) {
		this.stockSymbol = stockSymbol;
		this.commonDividend = commonDividend;
		this.preferredDividend = preferredDividend;
		this.parValue = parValue;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public Double getCommonDividend() {
		return commonDividend;
	}

	public void setCommonDividend(Double commonDividend) {
		this.commonDividend = commonDividend;
	}

	public Double getPreferredDividend() {
		return preferredDividend;
	}

	public void setPreferredDividend(Double preferredDividend) {
		this.preferredDividend = preferredDividend;
	}

	public Double getParValue() {
		return parValue;
	}

	public void setParValue(Double parValue) {
		this.parValue = parValue;
	}
}
