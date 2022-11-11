package br.com.inatel.quotationmanagement.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Maira ALves
 * @since Oct. 2022
 */

@Entity
public class Quote {
	
	@Id
	private String id;
	
	@NotNull
	private LocalDate dateQuote;
	
	@NotNull
	private double valueQuote;
	
	@ManyToOne
	private StockAux stockAux;
	
	@PrePersist
    private void onSave() {
		this.id = UUID.randomUUID().toString();
    }
	
	public Quote(StockAux stock, LocalDate dateQuote, double valueQuote) {
		this.stockAux = stock;
		this.dateQuote = dateQuote;
		this.valueQuote = valueQuote;
	}
	
	public Quote() {}

	/**
	 * Methods getters and setters
	 */
	
	public String getId() {
		return id;
	}

	public StockAux getStock() {
		return stockAux;
	}

	public LocalDate getDateQuote() {
		return dateQuote;
	}

	public double getValueQuote() {
		return valueQuote;
	}

	public void setValue(double valueQuote) {
		this.valueQuote = valueQuote;
	}
	
	
}
