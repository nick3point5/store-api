package com.store.storeAPI.api.combos;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComboRepository extends JpaRepository<Combo, Long> {
    Optional<Combo> findById(long id);

}
