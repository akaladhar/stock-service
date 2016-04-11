package com.jpm.assignment.data;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.jpm.assignment.pojo.Stock;
import com.jpm.assignment.pojo.StockTrade;

public class StockStore {

	/*
	 * Holds trades
	 */
	private List<StockTrade> trades = new ArrayList<StockTrade>();
	

	/*
	 * Holds information like last dividend, par value, preferred dividend percentatge about stocks 
	 */
	private List<Stock> stocks  = new ArrayList<Stock>();
	
	/*
	 * Holds latest stock prices
	 */
	private Map<String, Double> stockPrices = new HashMap<String, Double>();
	
	private StockStore(){
		
	}
	
	private static StockStore store = new StockStore();
	
	public static StockStore getStockStore(){
		return store;
	}
	
	
	public List<StockTrade> getTrades() {
		return trades;
	}
	
	public List<Stock> getStocks() {
		return stocks;
	}

	public Stock getStock(final String stockSymbol){
		return stocks.stream().filter(p -> p.getStockSymbol().equals(stockSymbol)).findFirst().get();
	}
	
	/**
	 * Updates existing stock or inserts new one if one does not exist
	 * @param stock
	 */
	public void updateStock(Stock stock){
		stocks.add(stock);
	}
	
	
	public void recordTrade(StockTrade trade){
		trades.add(trade);
		stockPrices.put(trade.getStockSymbol(), trade.getPrice());
	}
	
	/**
	 * Stock prices for index calculation
	 * Assuming index needs the latest price of stock
	 * @return
	 */
	public Collection<Double> getStockPrices(){
		return stockPrices.values();
	}
	
	
	public List<StockTrade> getStockPricesForLast15Minutes(String stockSymbol){
		
		long startTime = (new Date()).getTime() - (15 * 60 * 1000);
		return trades.stream().filter( 
				p -> (p.getStockSymbol().equals(stockSymbol) && p.getTimestamp() > startTime)).collect(Collectors.toList());
	}
	
}
