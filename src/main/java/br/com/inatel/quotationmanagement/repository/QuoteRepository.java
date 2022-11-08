package br.com.inatel.quotationmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.inatel.quotationmanagement.model.Quote;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, String> {
	
}
