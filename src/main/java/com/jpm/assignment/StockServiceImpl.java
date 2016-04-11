package com.jpm.assignment;

import java.util.List;
import com.jpm.assignment.data.StockStore;
import com.jpm.assignment.exception.InvalidDataException;
import com.jpm.assignment.pojo.BuySellIndicator;
import com.jpm.assignment.pojo.Stock;
import com.jpm.assignment.pojo.StockTrade;
/**
 * 
 * Implements stock operations
 * @author akaladhar
 *
 */
public class StockServiceImpl implements StockService {

	
	StockStore stockStore = StockStore.getStockStore();

	public Double getCommonDividendYield(String stockSymbol,
			Double tickerPrice) throws InvalidDataException {
		if (stockSymbol == null || tickerPrice == null || tickerPrice == 0d){
			throw new InvalidDataException("Ticker price can not be 0");
		}
		Stock stock = stockStore.getStock(stockSymbol);
		if (stock == null){
			throw new InvalidDataException("No stock found");
		}
		return (stockStore.getStock(stockSymbol).getCommonDividend()/tickerPrice)*100;
	}

	public Double getPreferredDividendYield(String stockSymbol,
			Double tickerPrice) throws InvalidDataException {
		if (stockSymbol == null || tickerPrice == null || tickerPrice == 0d){
			throw new InvalidDataException("Ticker price can not be 0");
		}
		Stock stock = stockStore.getStock(stockSymbol);
		if (stock == null){
			throw new InvalidDataException("No stock found");
		}
		return ((stock.getPreferredDividend()*(stock.getParValue()))/tickerPrice);
	}
	
	public Double getPERatio(String stockSymbol, Double tickerPrice) throws InvalidDataException {
		if (stockSymbol == null || tickerPrice == null || tickerPrice == 0d){
			throw new InvalidDataException("Ticker price can not be 0");
		}
		Stock stock = stockStore.getStock(stockSymbol);
		if (stock == null){
			throw new InvalidDataException("No stock found");
		}
		return tickerPrice/stockStore.getStock(stockSymbol).getCommonDividend();
	}

	public void recordTrade(String stockSymbol, long timestamp,
			Double quantity, Double price, BuySellIndicator buySell ) throws InvalidDataException {
		
		StockTrade trade = new StockTrade(stockSymbol, timestamp, quantity, price, buySell);
		if (stockSymbol == null || quantity == null|| price == null){
			throw new InvalidDataException("Trade data is not valid");
		}
		stockStore.recordTrade(trade);
	}

	public Double getStockPrice(String stockSymbol) throws InvalidDataException {
		
		if (stockSymbol == null){
			throw new InvalidDataException("No stock found");
		}
		/*
		 * get list of trades in last 15 minutes
		 */
		List<StockTrade> trades = stockStore.getStockPricesForLast15Minutes(stockSymbol);
		
		if (trades == null|| trades.size() == 0){
			throw new  InvalidDataException("No stock trades found");
		}
		Double sumOfPriceStarQuantity = 0d;
		Double totalQuantity = 0d;
		for(StockTrade trade: trades){
			sumOfPriceStarQuantity += trade.getPrice()*trade.getQuantity();
			totalQuantity += trade.getQuantity();
		}
		
		return sumOfPriceStarQuantity/totalQuantity;
	}


}
