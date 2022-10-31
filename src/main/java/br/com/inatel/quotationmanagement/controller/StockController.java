package br.com.inatel.quotationmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.inatel.quotationmanagement.controller.dto.StockQuoteDto;
import br.com.inatel.quotationmanagement.model.Stock;
import br.com.inatel.quotationmanagement.service.StockService;

@RestController
@RequestMapping("/stocks")
public class StockController {
	
	@Autowired
	private StockService stockService;
	
	@GetMapping
	public List<StockQuoteDto> list() {
		List<Stock> stocks = stockService.findAllWithQuotes();
		List<StockQuoteDto> StocksQuotesDto = StockQuoteDto.convert(stocks);
		return StocksQuotesDto;
}
}
