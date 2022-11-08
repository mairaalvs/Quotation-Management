package br.com.inatel.quotationmanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import br.com.inatel.quotationmanagement.controller.form.StockQuoteForm;
import br.com.inatel.quotationmanagement.model.StockAux;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class StockControllerTest {
	
	@Autowired
	private WebTestClient webTestClient;

//	given a read order (dado um pedido de leitura)
//	when receiving all the stocks (quando receber todos os estoques)
//	then it should return status 200 ok (então deve retornar status 200 ok)
			
	@Test
	void returnStatus200ByListingAllTheStockQuotes() {
		webTestClient.get()
		.uri("/stock")
		.exchange()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectStatus().isOk();
	}
	
//	given a read order by StockId (dado um pedido de leitura pelo StockId)
//	when receiving the stock (quando receber o estoque)
//	then it should return status 200 ok (então deve retornar status 200 ok)
			
	@Test
	void returnStatus200ListingStockQuotesByStockId() {		
		String stockId = "petr4";

        StockAux stock = webTestClient.get()
                .uri("/stock/" + stockId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(StockAux.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(stock);
        assertEquals(stock.getStockId(),stockId);
	}
	
//	given a read order by StockId invalid (dado um pedido de leitura pelo StockId invalido)
//	when receiving the stock (quando receber o estoque)
//	then it should return status 404 not found (então deve retornar status 404 not found)
			
	@Test
	void returnStatus404ListingStockQuotesByStockIdInvalid() {		
		String stockId = "invalid";

		String result = webTestClient.get()
                .uri("/stock/" + stockId)
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
        
		assertTrue(result.contains("Stock Not Found. Please check and retry the search!"));
	}

//	given valid StockId (dado um StockId valido)
//	when Post Quote (quando lançar uma cotação)
//	then it should return status 201 created (então deve retornar status 201 created)
	
	@Test
    void returnStatus201PostingQuoteByValidStockId() {
		Map<LocalDate, Double> quotesMap = new HashMap<>();
        LocalDate date = LocalDate.now();
        quotesMap.put(date, 13.0);
        StockQuoteForm stockQuoteForm = new StockQuoteForm("petr4", quotesMap);
        
        StockAux stock = webTestClient.post()
                .uri("/stock")
                .bodyValue(stockQuoteForm)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(StockAux.class)
                .returnResult().getResponseBody();

        assertNotNull(stock.getId());
        assertEquals("petr4",stock.getStockId());
    }
    
//	given invalid StockId (dado um StockId invalido)
//	when Post Quote (quando lançar uma cotação)
//	then it should return status 400 bad request (então deve retornar status 400 bad request)
	
	@Test
    void returnStatus400PostingQuoteByInvalidStockId() {
		Map<LocalDate, Double> quotesMap = new HashMap<>();
        LocalDate date = LocalDate.now();
        quotesMap.put(date, 13.0);
        StockQuoteForm stockQuoteForm = new StockQuoteForm("invalid", quotesMap);
        
        String result = webTestClient.post()
                .uri("/stock")
                .bodyValue(stockQuoteForm)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(String.class)
                .returnResult().getResponseBody();

        assertTrue(result.contains("Bad Request. Please verify that the stock was created correctly to create a quote"));
    }
	
	
	
}
