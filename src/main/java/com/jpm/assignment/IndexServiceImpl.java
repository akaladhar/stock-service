package com.jpm.assignment;

import java.util.Collection;

import com.jpm.assignment.data.StockStore;
/**
 * Implements index calculations
 * @author akaladhar
 *
 */
public class IndexServiceImpl implements IndexService {

	StockStore store = StockStore.getStockStore();
	
	@Override
	public Double getGBCEAllShareIndex() {
		
		Collection<Double> prices = store.getStockPrices();
		double totalPrice = prices.stream().reduce(0.0, (x,y) -> x + y);
		
		return Math.pow(totalPrice, 1.0/ prices.size());
	}

}
