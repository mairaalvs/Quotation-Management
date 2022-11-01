package br.com.inatel.quotationmanagement.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<StockQuoteDto> listOne(@PathVariable("stockId") String stockId) {
		Stock stock = stockService.findByStockId(stockId);

//		return stock.orElseThrow( () -> {
//			String msg = String.format("Nenhum curso encontrado com id [%s]", stockId);
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
//		});

		if (stock != null) {
			StockQuoteDto stockQuoteDto = StockQuoteDto.convertOneStock(stock);
			return ResponseEntity.ok(stockQuoteDto);
		}
		return ResponseEntity.notFound().build();

	}


}
