package br.com.inatel.quotationmanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.inatel.quotationmanagement.controller.form.StockQuoteForm;
import br.com.inatel.quotationmanagement.model.StockAux;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StockControllerTest {
	
	/**
	 * Given a read order
	 * When receiving all the stocks
	 * Then it should return status 200 ok
	 */
	@Test
	@Order(1)
	void givenAReadOrder_WhenReceivingAllTheStocks_ThenItShouldReturnStatus200Ok() {
		WebTestClient.bindToServer().baseUrl("http://localhost:8081").build().get()
		.uri("/stock")
		.exchange()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectStatus().isOk();
	}
	
	/**
	 * Given a read order by StockId valid
	 * When receiving the stock
	 * Then it should return status 200 ok
	 */
	@Test
	@Order(2)
	void givenAReadOrderByStockIdValid_WhenReceivingTheStock_ThenItShouldReturnStatus200Ok() {		
		String stockId = "petr4";

        StockAux stock = WebTestClient.bindToServer()
        		.baseUrl("http://localhost:8081").build()
        		.get()
                .uri("/stock/" + stockId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(StockAux.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(stock);
        assertEquals(stock.getStockId(),stockId);
	}
	
	/**
	 * Given a read order by StockId invalid
	 * When not receiving the stock
	 * Then it should return status 404 not found
	 */
	
	@Test
	@Order(3)
	void givenAReadOrderByStockIdInvalid_WhenNotReceivingTheStock_ThenItShouldReturnStatus404NotFound() {		
		String stockId = "invalid";

		String result = WebTestClient.bindToServer()
				.baseUrl("http://localhost:8081").build()
				.get()
                .uri("/stock/" + stockId)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
        
		assertTrue(result.contains("Stock Not Found. Please check and retry the search!"));
	}
	
	/**
	 * Given valid StockId
	 * When create stock with quotes in the correct structure
	 * Then it should return status 201 created
	 */
	@Test
	@Order(4)
    void givenValidStockId_WhenCreateStockWithQuotesInTheCorrectStructure_ThenItShouldReturnStatus201Created() {
		Map<LocalDate, Double> quotesMap = new HashMap<>();
        LocalDate date = LocalDate.now();
        quotesMap.put(date, 13.0);
        StockQuoteForm stockQuoteForm = new StockQuoteForm("petr1", quotesMap);
        
        StockAux stock = WebTestClient
        		.bindToServer().baseUrl("http://localhost:8081").build()
        		.post()
                .uri("/stock")
                .bodyValue(stockQuoteForm)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(StockAux.class)
                .returnResult().getResponseBody();

        assertNotNull(stock.getId());
        assertEquals("petr1",stock.getStockId());
    }
	
	/**
	 * Given valid StockId
	 * When add quotes in the correct structure
	 * Then it should return status 200 ok
	 */
	@Test
	@Order(5)
    void givenValidStockId_WhenAddQuotesInTheCorrectStructure_ThenItShouldReturnStatus200Ok() {
		Map<LocalDate, Double> quotesMap = new HashMap<>();
        LocalDate date = LocalDate.now();
        quotesMap.put(date, 20.0);
        StockQuoteForm stockQuoteForm = new StockQuoteForm("petr1", quotesMap);
        
        StockAux stock = WebTestClient
        		.bindToServer().baseUrl("http://localhost:8081").build()
        		.post()
                .uri("/stock")
                .bodyValue(stockQuoteForm)
                .exchange()
                .expectStatus().isOk()
                .expectBody(StockAux.class)
                .returnResult().getResponseBody();

        assertNotNull(stock.getId());
        assertEquals("petr1",stock.getStockId());
    }
	
	/**
	 * Given invalid StockId
	 * When try create or add stock and quotes 
	 * Then it should return status 404 not found
	 */
	@Test
	@Order(6)
    void givenInvalidStockId_WhenTryCreateOrAddStockAndQuotes_ThenItShouldReturnStatus404NotFound() {
		Map<LocalDate, Double> quotesMap = new HashMap<>();
        LocalDate date = LocalDate.now();
        quotesMap.put(date, 13.0);
        StockQuoteForm stockQuoteForm = new StockQuoteForm("invalid", quotesMap);
        
        String result = WebTestClient
        		.bindToServer().baseUrl("http://localhost:8081").build()
        		.post()
                .uri("/stock")
                .bodyValue(stockQuoteForm)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class)
                .returnResult().getResponseBody();

        assertTrue(result.contains("Stock Not Found. Please verify that the stock was created correctly to create a quote"));
    }
	
	/**
	 * Given a delete cache order
	 * Then it should return status 204 no content
	 */
	@Test
	@Order(7)
	void givenADeleteCacheOrder_ThenItShouldReturnStatus204NoContent() {
		WebTestClient.bindToServer()
		.baseUrl("http://localhost:8081").build()
		.delete()
		.uri("/stock/stockcache")
		.exchange()
		.expectStatus().isNoContent();
	}
	
}
