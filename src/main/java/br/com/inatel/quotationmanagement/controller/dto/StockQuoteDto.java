package br.com.inatel.quotationmanagement.controller.dto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.inatel.quotationmanagement.model.Quote;
import br.com.inatel.quotationmanagement.model.StockAux;

/**
 * 
 * @author Maira ALves
 * @since Oct. 2022
 */
public class StockQuoteDto {
	
	private String id;
	private String stockId;
	private Map<LocalDate, Double> quotesMap = new HashMap<>();

	public StockQuoteDto(StockAux stock) {
		this.id = stock.getId();
		this.stockId = stock.getStockId();
		stock.getQuotes().forEach(q -> quotesMap.put(q.getDateQuote(),q.getValueQuote()));
	}
	
	/**
	 * 
	 * @param stocks
	 * @return list of StockAux converted to StockQuotesDto
	 */
	public static List<StockQuoteDto> convert(List<StockAux> stocks) {
		return stocks.stream().map(StockQuoteDto::new).collect(Collectors.toList());
	}

	/**
	 * Methods getters
	 */
	
	public String getId() {
		return id;
	}

	public String getStockId() {
		return stockId;
	}

	public Map<LocalDate, Double> getQuotesMap() {
		return quotesMap;
	}
	
	
}
