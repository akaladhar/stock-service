package com.jpm.assignment;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.jpm.assignment.data.StockStore;
import com.jpm.assignment.exception.InvalidDataException;
import com.jpm.assignment.pojo.BuySellIndicator;
import com.jpm.assignment.pojo.Stock;
import com.jpm.assignment.pojo.StockTrade;

/**
 * Test class to test StockService methods
 * @author akaladhar
 *
 */
public class StockServiceTest {

	StockStore store = StockStore.getStockStore();
	@Before
	public void testSetup(){
		
	    long now = (new Date()).getTime();
		
		
		store.updateStock(new Stock("TEA", 0d, 0d, 100d));
		store.updateStock(new Stock("POP", 8d, 0d, 100d));
		store.updateStock(new Stock("ALE", 23d, 0d, 60d));
		store.updateStock(new Stock("GIN", 8d, 2d, 100d));
		store.updateStock(new Stock("JOE", 13d, 0d, 250d));
		
		store.recordTrade(new StockTrade("TEA", now - (5*10*1000), 100d, 100d, BuySellIndicator.BUY));
		store.recordTrade(new StockTrade("TEA", now - (10*60*1000), 200d, 101d, BuySellIndicator.BUY));
		
	}
	
	@Test
	public void testGetCommonDividendYield() throws InvalidDataException{
	
		StockService stockService = new StockServiceImpl();
		double yield = stockService.getCommonDividendYield("POP", 250d);
		
		assertTrue(yield == 3.2d );
	}
	
	@Test
	public void testGetPreferredDividendYield() throws InvalidDataException{
		StockService stockService = new StockServiceImpl();
		Double yield = stockService.getPreferredDividendYield("GIN", 110d);
		assertTrue((new BigDecimal(yield)).round( new MathContext(4)).doubleValue() == 1.818d );
	}
	
	@Test
	public void testGetPERatio() throws InvalidDataException{
		StockService stockService = new StockServiceImpl();
		double peRatio = stockService.getPERatio("GIN", 110d);
		assertTrue((new BigDecimal(peRatio)).round( new MathContext(4)).doubleValue() == 13.75d);
	}
	
	@Test
	public void testRecordTrade() throws InvalidDataException{
		StockService stockService = new StockServiceImpl();
		
		int existingTrade = store.getTrades().size();
		stockService.recordTrade("TEA", (new Date()).getTime(), 100d, 50d, BuySellIndicator.BUY);
		
		assertTrue(existingTrade+1 == store.getTrades().size());
		
	}
	
	@Test
	public void testGetStockPrice() throws InvalidDataException{
		long now = (new Date()).getTime();
		StockService stockService = new StockServiceImpl();
		store.recordTrade(new StockTrade("TEA", now, 100d, 100d, BuySellIndicator.BUY));
		//this record should not be considered for stock price calculation as it was recorded 20 minutes before the current time.
		store.recordTrade(new StockTrade("TEA", now - (20*60*1000), 200d, 101d, BuySellIndicator.BUY));
		double stockPrice = stockService.getStockPrice("TEA");
		assertTrue(stockPrice == 100.5d);
	}
}
