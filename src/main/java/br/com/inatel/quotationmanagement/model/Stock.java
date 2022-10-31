package br.com.inatel.quotationmanagement.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Stock {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String stockId;
	
	@OneToMany(mappedBy = "stock")
	private List<Quote> quotes = new ArrayList<>();

	public Stock(Long id, String stockId) {
		this.id = id;
		this.stockId = stockId;
	}
	
//	@PrePersist
//	private void onSave() {
//		this.id = UUID.randomUUID().toString();
//	}
	
	public Stock() {}

	public Long getId() {
		return id;
	}

	public String getStockId() {
		return stockId;
	}

	public List<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}
	
}
