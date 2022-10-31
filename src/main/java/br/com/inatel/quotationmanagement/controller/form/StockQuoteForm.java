package br.com.inatel.quotationmanagement.controller.form;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;

public class StockQuoteForm {
	
//	@NotNull @NotEmpty @Length(min = 3)
	private String stockId;
	
//	@NotNull @NotEmpty
	private Map<LocalDate, Double> quotesMap = new HashMap<LocalDate, Double>();
}
