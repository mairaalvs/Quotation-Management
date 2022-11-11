package br.com.inatel.quotationmanagement.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.inatel.quotationmanagement.controller.dto.StockQuoteDto;
import br.com.inatel.quotationmanagement.model.StockAux;

@SpringBootTest
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
	public void returnAllListOfStocks() {
		List<StockAux> stockQuotes = stockService.findAll();
		List<StockQuoteDto> stockQuotesDto = StockQuoteDto.convert(stockQuotes);

		assertEquals(stockQuotesDto.isEmpty(), false);
		assertEquals(stockQuotesDto.size(), stockQuotes.size());
//		assertEquals(stockQuotesDto.get(0).getStockId(), "petr3");
//		assertEquals(stockQuotesDto.get(0).getQuotesMap().size(), 0);
	}
	
	/** Given a get on stock by stockId valid
	 * When you get it
	 * Then check the information
	 */
	@Test
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
	void returnListOfQuotesEmptyByStockIdInvalid() {
		Optional<StockAux> OpStock = stockService.findByStockId("petr5");
		
		assertTrue(OpStock.isEmpty());
	}
	
	/**
	 * Saving a stock in the database
	 */
	@Test
	void returnAValidStockSaveAtDb() {
		StockAux toSave = new StockAux("petr1");
		stockService.save(toSave);
		Optional<StockAux> stockFound = stockService.findByStockId(toSave.getStockId());
		
		assertNotEquals(null, stockFound);
	}
}
