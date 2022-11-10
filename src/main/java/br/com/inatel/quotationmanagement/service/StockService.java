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
import br.com.inatel.quotationmanagement.controller.dto.StockDto;
import br.com.inatel.quotationmanagement.controller.dto.StockManagerDto;
import br.com.inatel.quotationmanagement.controller.dto.StockQuoteDto;
import br.com.inatel.quotationmanagement.controller.form.StockQuoteForm;
import br.com.inatel.quotationmanagement.model.Quote;
import br.com.inatel.quotationmanagement.model.StockAux;
import br.com.inatel.quotationmanagement.repository.QuoteRepository;
import br.com.inatel.quotationmanagement.repository.StockRepository;

@Service
@Transactional
public class StockService {

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	QuoteRepository quoteRepository;

	@Autowired
	StockManagerAdapter stockManagerAdapter;

	public List<StockAux> findAll() {
		List<StockAux> stocks = stockRepository.findAll();
		stocks.forEach(s -> s.getQuotes().size());
		return stocks;
	}

	public Optional<StockAux> findByStockId(String stockId) {
		StockAux stock = stockRepository.findOneStockByStockId(stockId);
				
		if (stock != null) {
			stock.getQuotes().size();
			return Optional.of(stock);
		}
		return Optional.empty();
	}

	public void saveQuotes(List<Quote> quotes) {
		quotes.forEach(q -> quoteRepository.save(q));
	}

	public void save(StockAux stock) {
		stockRepository.save(stock);
	}

	public void deleteCache() {
		System.out.println("The cache was cleaned!");
	}

	public boolean existAtStockManager(String stockId) {
		List<StockManagerDto> stocksAtManager = stockManagerAdapter.listAll();
		return stocksAtManager.stream().anyMatch(s -> s.getId().equals(stockId));
	}
	
	@CacheEvict(value = "stockList", allEntries = true)
    public ResponseEntity<?> delete(String stockId){
        Optional<StockAux> opStock = stockRepository.findByStockId(stockId);
        if(opStock.isPresent()){
            List<Quote> quotes = opStock.get().getQuotes();
            stockRepository.delete(opStock.get());
            quoteRepository.deleteAll(quotes);
            return new ResponseEntity<>("Delete",HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("StockId Not Found. Please check and retry the search!",HttpStatus.NOT_FOUND);
    }  
}
