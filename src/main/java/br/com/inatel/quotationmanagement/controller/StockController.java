package br.com.inatel.quotationmanagement.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
import br.com.inatel.quotationmanagement.model.StockAux;
import br.com.inatel.quotationmanagement.service.StockService;


/**
 * 
 * @author Maira ALves
 * @since Oct. 2022
 */

@RestController
@RequestMapping("/stock")
public class StockController {

	@Autowired
	private StockService stockService;

	/**
	 * the list of all stocks and their quotes already entered
	 * @return
	 */
	@GetMapping
	@Cacheable(value = "stocksList")
	public List<StockQuoteDto> list() {
		List<StockAux> stocks = stockService.findAll();
		List<StockQuoteDto> StocksQuotesDto = StockQuoteDto.convert(stocks);
		return StocksQuotesDto;
	}

	/**
	 * 
	 * @param stockId
	 * @return only Stock searched by your stockId 
	 */
	@GetMapping("/{stockId}")
	public ResponseEntity<?> listOne(@PathVariable("stockId") String stockId) {

		Optional<StockAux> OpStock = stockService.findByStockId(stockId);

		if (OpStock.isPresent()) {
			StockAux stockQuote = OpStock.get();
			StockQuoteDto stockQuotesDto = new StockQuoteDto(stockQuote);
			return new ResponseEntity<>(stockQuotesDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Stock Not Found. Please check and retry the search!", HttpStatus.NOT_FOUND);
		}
		
	}

	/**
	 * 
	 * @param form
	 * @return ResponseEntity and response body whether Stock was created or not 
	 */
	@PostMapping
	@CacheEvict(value = "stocksList", allEntries = true)
	public ResponseEntity<?> createStockQuote(@RequestBody @Valid StockQuoteForm form) {

		Optional<StockAux> opStock = stockService.findByStockId(form.getStockId());
		StockAux stock = form.convert();

		if (opStock.isPresent()) {
			stock = opStock.get();
			form.addQuote(stock);
			stockService.saveDbQuotes(stock.getQuotes());
			return new ResponseEntity<>(new StockQuoteDto(stock), HttpStatus.OK);
		}
		else if(stockService.existsAtStockManager(stock)){
			stock = stockService.saveDbStock(stock);
			form.addQuote(stock);
			stockService.saveDbQuotes(stock.getQuotes());
			return new ResponseEntity<>(new StockQuoteDto(stock), HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>("Bad Request. Please verify that the stock was created correctly to create a quote",HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * @return Clean cache
	 */
	@DeleteMapping("/stockcache")
	@CacheEvict(value = "stocksAtManagerList")
    public ResponseEntity<?> deleteCache(){
		stockService.deleteCache();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	


}