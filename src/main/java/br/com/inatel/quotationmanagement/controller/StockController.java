package br.com.inatel.quotationmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.inatel.quotationmanagement.controller.dto.StockQuoteDto;
import br.com.inatel.quotationmanagement.controller.form.StockQuoteForm;
import br.com.inatel.quotationmanagement.model.Stock;
import br.com.inatel.quotationmanagement.service.StockService;

@RestController
@RequestMapping("/stock")
public class StockController {

	@Autowired
	private StockService stockService;

	@GetMapping
	public List<StockQuoteDto> list() {
		List<Stock> stocks = stockService.findAll();
		List<StockQuoteDto> StocksQuotesDto = StockQuoteDto.convert(stocks);
		return StocksQuotesDto;
	}

	@GetMapping("/{stockId}")
	public ResponseEntity<?> listOne(@PathVariable("stockId") String stockId) {

		Optional<Stock> OpStock = stockService.findByStockId(stockId);

		if (OpStock.isPresent()) {
			Stock stockQuote = OpStock.get();
			StockQuoteDto stockQuotesDto = new StockQuoteDto(stockQuote);
			return new ResponseEntity<>(stockQuotesDto, HttpStatus.OK);
		}
		return new ResponseEntity<>("Stock Not Found. Please check and retry the search!", HttpStatus.NOT_FOUND);
	}

	@PostMapping("/stock")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<StockQuoteDto> saveStock(@RequestBody StockQuoteForm form) {

		Optional<Stock> OpStock = stockService.findByStockId(form.getStockId());

		if (OpStock.isPresent()) {
			Stock stock = OpStock.get();
			form.addQuote(stock);
			stockService.saveQuotes(stock.getQuotes());
			return new ResponseEntity<>(new StockQuoteDto(stock), HttpStatus.CREATED);
		} else {
			Stock stock = form.convert();
			stockService.save(stock);
			form.addQuote(stock);
			stockService.saveQuotes(stock.getQuotes());
			return new ResponseEntity<>(new StockQuoteDto(stock), HttpStatus.CREATED);
		}
	}
	
	@DeleteMapping("/stockcache")
	public void delete() {
		stockService.delete();
	}

}
