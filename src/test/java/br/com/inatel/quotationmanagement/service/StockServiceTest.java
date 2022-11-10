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
	
//	given a get on all stock (dado um get em todo o estoque)
//	when receive all stocks (quando receber todos os estoques)
//	then check the information (então verifico as informações)
	
	@Test
	public void returnAllListOfStocks() {
		List<StockAux> stockQuotes = stockService.findAll();
		List<StockQuoteDto> stockQuotesDto = StockQuoteDto.convert(stockQuotes);

		assertEquals(stockQuotesDto.isEmpty(), false);
		assertEquals(stockQuotesDto.size(), stockQuotes.size());
//		assertEquals(stockQuotesDto.get(0).getStockId(), "petr3");
//		assertEquals(stockQuotesDto.get(0).getQuotesMap().size(), 0);
	}
	
//	given a get on stock valid (dado um get em um estoque valido)
//	(quando recebe-lo)
//	(então verifico suas informações)
	
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
	
//	given Get All Stock (dado um get em um estoque invalido)
//	(então returno lista de Cotações Vazia)
	
	@Test
	void returnListOfQuotesEmptyByStockIdInvalid() {
		Optional<StockAux> OpStock = stockService.findByStockId("petr5");
		
		assertTrue(OpStock.isEmpty());
	}
	
	//salvando um estoque no banco de dados
	@Test
	void returnAValidStockSaveAtDb() {
		StockAux toSave = new StockAux("mgl");
		stockService.save(toSave);
		Optional<StockAux> stockFound = stockService.findByStockId(toSave.getStockId());
		
		assertNotEquals(null, stockFound);
	}
}
