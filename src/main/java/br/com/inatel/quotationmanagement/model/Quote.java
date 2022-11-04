package br.com.inatel.quotationmanagement.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class Quote {
	
	@Id
	private String id;
	
	private LocalDate dateQuote;
	private double valueQuote;
	
	@ManyToOne
	private Stock stock;
	
	@PrePersist
    private void onSave() {
		this.id = UUID.randomUUID().toString();
    }
	
	public Quote(Stock stock, LocalDate dateQuote, double valueQuote) {
		this.stock = stock;
		this.dateQuote = dateQuote;
		this.valueQuote = valueQuote;
	}
	
	public Quote() {}

	public String getId() {
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
		Quote other = (Quote) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
