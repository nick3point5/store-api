package com.store.storeAPI.api.discounts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscountRepository extends JpaRepository<Discount, Long> {
	Optional<Discount> findById(long id);

}
