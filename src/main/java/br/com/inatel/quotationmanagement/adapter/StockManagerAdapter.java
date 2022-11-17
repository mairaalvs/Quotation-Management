package br.com.inatel.quotationmanagement.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.inatel.quotationmanagement.controller.dto.StockManagerDto;
import reactor.core.publisher.Flux;

/**
 * 
 * @author Maira ALves
 * @since Oct. 2022
 */
@Service
public class StockManagerAdapter {
	
	@Value("${URL_MANAGER}")
	private String URL_MANAGER;
	
	@Value("${server.host}")
	private String host;
	
	@Value("${url.port}")
	private int port;
	
	@Cacheable(value = "stocksAtManagerList")
	public List<StockManagerDto> listAllStocks() {
		
		List<StockManagerDto> stocksAtManager = new ArrayList<>();
		
		Flux<StockManagerDto> fluxDto = WebClient.builder().baseUrl("http://" + URL_MANAGER).build()
				.get()
				.uri("/stock")
				.retrieve()
				.bodyToFlux(StockManagerDto.class);
		
		fluxDto.subscribe(f -> stocksAtManager.add(f));
	    fluxDto.blockLast();
		
	    return stocksAtManager;
	}
	
}
