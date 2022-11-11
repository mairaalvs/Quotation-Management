package br.com.inatel.quotationmanagement.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.inatel.quotationmanagement.model.Quote;

/**
 * 
 * @author Maira ALves
 * @since Oct. 2022
 */
public class QuoteDto {
	
	private String id;
	private LocalDate dateQuoteDto;
	private double valueQuoteDto;
	
	public QuoteDto(Quote quote) {
		this.id = quote.getId();
		this.dateQuoteDto = quote.getDateQuote();
		this.valueQuoteDto = quote.getValueQuote();
	}
	
	/**
	 * 
	 * @param quotes
	 * @return the list of quotesDto
	 */
	public static List<QuoteDto> convert(List<Quote> quotes) {
		return quotes.stream().map(QuoteDto::new).collect(Collectors.toList());
	}
	
	/**
	 * Methods getters
	 */

	public String getId() {
		return id;
	}
	public LocalDate getDateQuoteDto() {
		return dateQuoteDto;
	}
	public double getValueQuoteDto() {
		return valueQuoteDto;
	}
	
	
}
