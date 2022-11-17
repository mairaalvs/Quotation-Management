package br.com.inatel.quotationmanagement.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.inatel.quotationmanagement.adapter.StockManagerAdapter;
import br.com.inatel.quotationmanagement.controller.dto.StockManagerDto;
import br.com.inatel.quotationmanagement.model.Quote;
import br.com.inatel.quotationmanagement.model.StockAux;
import br.com.inatel.quotationmanagement.repository.QuoteRepository;
import br.com.inatel.quotationmanagement.repository.StockRepository;

/**
 * 
 * @author Maira ALves
 * @since Oct. 2022
 */

@Service
@Transactional
public class StockService {

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	private QuoteRepository quoteRepository;

	@Autowired
	private StockManagerAdapter stockManagerAdapter;

	/**
	 * 
	 * @return all the list of stocks
	 */
	public List<StockAux> findAll() {
		List<StockAux> stocks = stockRepository.findAll();
		stocks.forEach(s -> s.getQuotes().size());
		return stocks;
	}

	/**
	 * 
	 * @param stockId
	 * @return stock find by your stockId
	 */
	public Optional<StockAux> findByStockId(String stockId) {
		Optional<StockAux> optionalStock = stockRepository.findByStockId(stockId);
		optionalStock.map(stock -> stock.getQuotes().size());
		return optionalStock;
	}
	
	/**
	 * 
	 * @param stock
	 * @return if the stock is valid in stock manager
	 */
	public boolean existsAtStockManager(StockAux stock) {
		List<StockManagerDto> stocksAtManager = stockManagerAdapter.listAllStocks();
		return stocksAtManager.stream().anyMatch(i -> i.getId().equals(stock.getStockId()));
	}

	/**
	 * 
	 * @param quotes
	 */
	public void saveDbQuotes(List<Quote> quotes) {
		quotes.forEach(q -> quoteRepository.save(q));
	}	

	/**
	 * 	
	 * @param stock
	 * @return stock
	 */
	public StockAux saveDbStock(StockAux stock) {
		stock = stockRepository.save(stock);
		return stock;
	}

	public void deleteCache() {
		System.out.println("The cache was cleaned!");
	}
	

}
