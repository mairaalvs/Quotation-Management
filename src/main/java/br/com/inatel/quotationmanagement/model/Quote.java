package br.com.inatel.quotationmanagement.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Quote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate dateQuote;
	private double valueQuote;
	
	@ManyToOne
	private Stock stock;
	
	public Quote(Stock stock, LocalDate dateQuote, double valueQuote) {
		this.stock = stock;
		this.dateQuote = dateQuote;
		this.valueQuote = valueQuote;
	}
	
//	@PrePersist
//    private void onSave() {
//		this.id = UUID.randomUUID().toString();
//    }
	
	public Quote() {}

	public Long getId() {
		return id;
	}

	public Stock getStock() {
		return stock;
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
