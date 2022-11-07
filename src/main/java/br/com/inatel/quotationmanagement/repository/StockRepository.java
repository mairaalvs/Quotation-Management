package br.com.inatel.quotationmanagement.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.inatel.quotationmanagement.model.StockAux;

@Repository
public interface StockRepository extends JpaRepository<StockAux, String> {
	
	Optional<StockAux> findStockByStockId(String id);
	
	StockAux findOneStockByStockId(String stockId);
}
