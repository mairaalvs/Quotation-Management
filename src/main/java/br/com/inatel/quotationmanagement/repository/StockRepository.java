package br.com.inatel.quotationmanagement.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.inatel.quotationmanagement.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
	
	Optional<Stock> findByStockId(Long id);
}
