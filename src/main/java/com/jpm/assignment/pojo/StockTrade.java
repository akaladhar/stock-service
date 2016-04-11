package com.jpm.assignment.pojo;



/**
 * Holds Stock trade information
 * @author akaladhar
 *
 */
public class StockTrade {

	private String stockSymbol;
	
	private Double quantity;
	
	private long timestamp;
	
	private Double price; 
	
	private BuySellIndicator buySell;

	public StockTrade(String stockSymbol, 
			long timestamp, 
			Double quantity, 
			Double price, 
			BuySellIndicator buySell){
		this.stockSymbol = stockSymbol;
		this.timestamp = timestamp;
		this.quantity = quantity;
		this.price = price;
		this.buySell = buySell;
	}
	
	public String getStockSymbol() {
		return stockSymbol;
	}

	public Double getQuantity() {
		return quantity;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public Double getPrice() {
		return price;
	}

	public BuySellIndicator getBuySell() {
		return buySell;
	}

}


