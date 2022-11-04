package br.com.inatel.quotationmanagement.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.inatel.quotationmanagement.adapter.StockManagerAdapter;
import br.com.inatel.quotationmanagement.controller.dto.StockManagerDto;
import br.com.inatel.quotationmanagement.model.Quote;
import br.com.inatel.quotationmanagement.model.Stock;
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

	@Cacheable(value = "stocksList")
	public List<Stock> findAll() {
		List<Stock> stocks = stockRepository.findAll();
		stocks.forEach(s -> s.getQuotes().size());
		return stocks;
	}

	public Optional<Stock> findByStockId(String stockId) {
		Stock stock = stockRepository.findOneStockByStockId(stockId);
		if (stock != null) {
			stock.getQuotes().size();
			return Optional.of(stock);
		}
		return Optional.empty();
	}

	@CacheEvict(value = "stocksList", allEntries = true)
	public void saveQuotes(List<Quote> quotes) {
		quotes.forEach(q -> quoteRepository.save(q));
	}

	@CacheEvict(value = "stocksList", allEntries = true)
	public void save(Stock stock) {
		stockRepository.save(stock);
	}

	@CacheEvict(value = "stocksList", allEntries = true)
	public void delete() {
		System.out.println("Deletado!");
	}

	public boolean existAtStockManager(String stockId) {
		List<StockManagerDto> stocksAtManager = stockManagerAdapter.listAll();
		return stocksAtManager.stream().anyMatch(s -> s.getId().equals(stockId));
	}
}
