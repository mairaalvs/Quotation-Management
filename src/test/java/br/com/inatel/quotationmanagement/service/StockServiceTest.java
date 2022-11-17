package br.com.inatel.quotationmanagement.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.inatel.quotationmanagement.controller.dto.StockManagerDto;
import br.com.inatel.quotationmanagement.controller.dto.StockQuoteDto;
import br.com.inatel.quotationmanagement.controller.form.StockQuoteForm;
import br.com.inatel.quotationmanagement.model.Quote;
import br.com.inatel.quotationmanagement.model.StockAux;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class StockServiceTest {

	@Autowired
	private StockService stockService;
	
	/**
	 * Given a get on all stock
	 * When receive all stocks
	 * Then check the information
	 */	
	@Test
	@Order(1)
	public void returnAllListOfStocks() {
		List<StockAux> stockQuotes = stockService.findAll();
		List<StockQuoteDto> stockQuotesDto = StockQuoteDto.convert(stockQuotes);

		assertEquals(stockQuotesDto.isEmpty(), false);
		assertEquals(stockQuotesDto.size(), stockQuotes.size());
	}
	
	/** Given a get on stock by stockId valid
	 * When you get it
	 * Then check the information
	 */
	@Test
	@Order(2)
	public void returnListOfQuotesByStockIdValid() {
		List<StockAux> stockQuotes = new ArrayList<>();
		Optional<StockAux> OpStock = stockService.findByStockId("petr4");
	
		stockQuotes.add(OpStock.get());
		List<StockQuoteDto> stockQuotesDto = StockQuoteDto.convert(stockQuotes);
		
		assertEquals(stockQuotesDto.isEmpty(), false);
		assertEquals(stockQuotesDto.get(0).getStockId(), "petr4");
		assertEquals(stockQuotesDto.get(0).getQuotesMap().size(), 3);
	}
	
	/**
	 * Given a get on stock by stockId invalid
	 * Then return empty quotes list
	 */
	@Test
	@Order(3)
	void returnListOfQuotesEmptyByStockIdInvalid() {
		Optional<StockAux> OpStock = stockService.findByStockId("invalid");
		
		assertTrue(OpStock.isEmpty());
	}
	
	@Test
	@Order(4)
	void returnExistsAtStockManagerByStockIdValid() {
		StockAux stock = new StockAux("petr4");
		boolean stocksAtManager = stockService.existsAtStockManager(stock);
		
		assertEquals(stocksAtManager, true);
	}
	
	/**
	 * Saving a stock in the database
	 */
	@Test
	@Order(5)
	void returnAValidStockSaveAtDb() {
		StockAux toSave = new StockAux("abev3");
		stockService.saveDbStock(toSave);
		Optional<StockAux> stockFound = stockService.findByStockId(toSave.getStockId());
		
		assertNotEquals(null, stockFound);
	}
	
	@Test
	@Order(6)
	void returnExistsAtStockManagerByStockIdInvalid() {
		StockAux stock = new StockAux("invalid");
		boolean stocksAtManager = stockService.existsAtStockManager(stock);
		
		assertEquals(stocksAtManager, false);
	}
}
