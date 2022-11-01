package br.com.inatel.quotationmanagement.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Stock {
	
	@Id
	private String id;
	
	private String stockId;
	
	@OneToMany(mappedBy = "stock")
	private List<Quote> quotes = new ArrayList<>();
	
	@PrePersist
	private void onSave() {
		this.id = UUID.randomUUID().toString();
	}

	public Stock(String id, String stockId) {
		this.id = id;
		this.stockId = stockId;
	}
	
	public Stock() {}

	public Stock(String stockId) {
		this.stockId = stockId;
	}

	public String getId() {
		return id;
	}

	public String getStockId() {
		return stockId;
	}

	public List<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(Quote quote) {
		this.quotes.add(quote);
	}
	
}
