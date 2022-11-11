package br.com.inatel.quotationmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.inatel.quotationmanagement.model.Quote;

/**
 * 
 * @author Maira ALves
 * @since Oct. 2022
 */
@Repository
public interface QuoteRepository extends JpaRepository<Quote, String> {
	
}
