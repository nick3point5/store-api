package com.store.storeAPI.receipts;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
	Optional<Receipt> findById(long id);

	List<Receipt> findByCartId(long cartId);

	@Query(value = "select * from receipts where cart_id = ?1 and product_id = ?2", nativeQuery = true)
	Optional<Receipt> findByCartIdAndProductId(long cartId, long productId);

}


