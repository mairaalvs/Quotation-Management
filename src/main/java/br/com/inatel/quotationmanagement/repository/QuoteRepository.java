package br.com.inatel.quotationmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.inatel.quotationmanagement.model.Quote;

public interface QuoteRepository extends JpaRepository<Quote, String> {

}
