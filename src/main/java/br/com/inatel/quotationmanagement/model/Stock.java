package br.com.inatel.quotationmanagement.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
	
	@Id @NotNull
	private String id;
	
	@NotNull
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
