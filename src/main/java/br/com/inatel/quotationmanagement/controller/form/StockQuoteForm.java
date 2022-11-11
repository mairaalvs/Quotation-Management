package br.com.inatel.quotationmanagement.controller.form;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.inatel.quotationmanagement.model.Quote;
import br.com.inatel.quotationmanagement.model.StockAux;

/**
 * 
 * @author Maira ALves
 * @since Oct. 2022
 */
public class StockQuoteForm {
	
	@NotNull @NotEmpty @Length(min = 2)
	private String stockId;
	
	@NotNull @NotEmpty
	private Map<LocalDate, Double> quotesMap = new HashMap<LocalDate, Double>();
	
	/**
	 * Instantiates a new stock quote form.
	 * @param stockId
	 * @param quotesMap
	 */
	public StockQuoteForm(String stockId, Map<LocalDate, Double> quotesMap) {
		this.stockId = stockId;
		this.quotesMap = quotesMap;
	}
	
	/**
	 * 
	 * @param stock
	 * @return list of required stock quotes
	 */
	public List<Quote> addQuote(StockAux stock){
		List<Quote> quotes = new ArrayList<>();
		
		quotesMap.forEach((d, v) -> {
			Quote quote = new Quote(stock, d,v);
			quotes.add(quote);
			stock.setQuotes(quote);
		});	
		return stock.getQuotes();
	}
	
	/**
	 * 
	 * @return the stock
	 */
	public StockAux convert() {
		return new StockAux(stockId);
	}
	
	/**
	 * Methods getters and setters
	 */
	
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
