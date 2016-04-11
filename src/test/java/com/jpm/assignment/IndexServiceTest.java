package com.jpm.assignment;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.jpm.assignment.data.StockStore;
import com.jpm.assignment.pojo.BuySellIndicator;
import com.jpm.assignment.pojo.Stock;
import com.jpm.assignment.pojo.StockTrade;

public class IndexServiceTest {
	
	@Before
	public void testSetup(){
		
		StockStore store = StockStore.getStockStore();
	    long now = (new Date()).getTime();
		
		
		store.updateStock(new Stock("TEA", 0d, 0d, 100d));
		store.updateStock(new Stock("POP", 8d, 0d, 100d));
		store.updateStock(new Stock("ALE", 23d, 0d, 60d));
		store.updateStock(new Stock("GIN", 8d, 2d, 100d));
		store.updateStock(new Stock("JOE", 13d, 0d, 250d));
		
		store.recordTrade(new StockTrade("TEA", now - (5*10*1000), 100d, 100d, BuySellIndicator.BUY));
		store.recordTrade(new StockTrade("TEA", now - (10*60*1000), 200d, 101d, BuySellIndicator.BUY));
		store.recordTrade(new StockTrade("POP", now - (10*60*1000), 200d, 96d, BuySellIndicator.BUY));
		store.recordTrade(new StockTrade("GIN", now - (10*60*1000), 200d, 30d, BuySellIndicator.BUY));
		
		
	}
	
	@Test
	public void testGetGBCEAllShareIndex(){
		IndexService service = new IndexServiceImpl();
		double gbceShareIndex = service.getGBCEAllShareIndex();
		assertTrue((new BigDecimal(gbceShareIndex)).round( new MathContext(4)).doubleValue() == 6.100d);
	}
}
