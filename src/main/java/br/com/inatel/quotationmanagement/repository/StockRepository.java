package br.com.inatel.quotationmanagement.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.inatel.quotationmanagement.model.StockAux;

/**
 * 
 * @author Maira ALves
 * @since Oct. 2022
 */
@Repository
public interface StockRepository extends JpaRepository<StockAux, String> {
	
	StockAux findOneStockByStockId(String stockId);
	
	Optional<StockAux> findByStockId(String stockId);
	
}
