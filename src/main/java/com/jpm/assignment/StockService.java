package com.jpm.assignment;



import com.jpm.assignment.exception.InvalidDataException;
import com.jpm.assignment.pojo.BuySellIndicator;

public interface StockService {

	public Double getCommonDividendYield(String stockSymbol, Double tickerPrice) throws InvalidDataException;
	
	public Double getPreferredDividendYield(String stockSymbol, Double tickerPrice) throws InvalidDataException;
	
	public Double getPERatio(String stockSymol, Double tickerPrice) throws InvalidDataException;
	
	public void recordTrade(String stockSymbol, 
			long timestamp,
			Double quantity, 
			Double price, 
			BuySellIndicator buySell) throws InvalidDataException;
	
	public Double getStockPrice(String stockSymbol) throws InvalidDataException;
	
	
}
