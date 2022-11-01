package br.com.inatel.quotationmanagement.controller.form;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.inatel.quotationmanagement.model.Quote;
import br.com.inatel.quotationmanagement.model.Stock;
import br.com.inatel.quotationmanagement.repository.StockRepository;

public class StockQuoteForm {
	
	@NotNull @NotEmpty @Length(min = 5)
	private String stockId;
	private LocalDate dateQuote;
    private Double valueQuote;
	
	private Map<LocalDate, Double> quotesMap = new HashMap<LocalDate, Double>();
	
	public StockQuoteForm(String stockId, Map<LocalDate, Double> quotesMap) {
		this.stockId = stockId;
		this.quotesMap = quotesMap;
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
