package br.com.inatel.quotationmanagement.controller.form;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.inatel.quotationmanagement.model.Quote;
import br.com.inatel.quotationmanagement.model.Stock;

public class StockQuoteForm {
	
	private String stockId;
	
	private Map<LocalDate, Double> quotesMap = new HashMap<LocalDate, Double>();
	
	public StockQuoteForm(String stockId, Map<LocalDate, Double> quotesMap) {
		this.stockId = stockId;
		this.quotesMap = quotesMap;
	}
	
	public List<Quote> addQuote(Stock stock){
		List<Quote> quotes = new ArrayList<>();
		
		quotesMap.forEach((d, v) -> {
			Quote quote = new Quote(stock, d,v);
			quotes.add(quote);
			stock.setQuotes(quote);
		});	
		return stock.getQuotes();
	}
	
	public Stock convert() {
		return new Stock(stockId);
	}
	
	public String getStockId() {
		return stockId;
	}
	
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	
	public Map<LocalDate, Double> getQuotesMap() {
		return quotesMap;
	}
	
	public void setQuotesMap(Map<LocalDate, Double> quotesMap) {
		this.quotesMap = quotesMap;
	}
}
