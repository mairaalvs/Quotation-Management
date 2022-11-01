package br.com.inatel.quotationmanagement.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.inatel.quotationmanagement.model.Quote;
import br.com.inatel.quotationmanagement.model.Stock;

public class StockDto {
	
	private String id;
	private String stockId;
	private List<Quote> quotes = new ArrayList<>();
	
	public StockDto(Stock stock) {
		this.id = stock.getId();
		this.stockId = stock.getStockId();
		this.quotes = stock.getQuotes();
	}
	
	public String getId() {
		return id;
	}
	
	public String getStockId() {
		return stockId;
	}

	public List<QuoteDto> getQuotes() {
		return QuoteDto.convert(quotes);
	}
	
	public static List<StockDto> convert(List<Stock> stocks) {
		return stocks.stream().map(StockDto::new).collect(Collectors.toList());
	}
	
	
}
