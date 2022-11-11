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

/**
 * 
 * @author Maira ALves
 * @since Oct. 2022
 */

@Entity
public class StockAux {
	
	@Id @NotNull
	private String id;
	
	@NotNull
	private String stockId;
	
	@OneToMany(mappedBy = "stockAux") 
	private List<Quote> quotes = new ArrayList<>();
	
	@PrePersist
	private void onSave() {
		this.id = UUID.randomUUID().toString();
	}

	public StockAux(String id, String stockId) {
		this.id = id;
		this.stockId = stockId;
	}
	
	public StockAux() {}

	public StockAux(String stockId) {
		this.stockId = stockId;
	}

	/**
	 * Methods getters and setters
	 */
	
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
