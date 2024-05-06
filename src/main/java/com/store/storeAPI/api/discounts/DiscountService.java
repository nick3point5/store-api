package com.store.storeAPI.api.discounts;

import com.store.storeAPI.api.discounts.Discount;
import com.store.storeAPI.api.discounts.DiscountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {
    private final DiscountRepository discountRepository;

    @Autowired
    public DiscountService(DiscountRepository discountsRepository) {
        this.discountRepository = discountsRepository;
    }

    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    public Discount getDiscountById(Long id) {
        return discountRepository
                .findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("Discount with id " + id + " does not exist")
                );
    }
    public Discount addDiscount(Discount discount) {
        return discountRepository.save(discount);
    }
    @Transactional
    public Discount updateDiscount(Long id, Discount discount) {
        boolean exists = discountRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Discount with id " + discount.getId() + " does not exist");
        }

        discount.setId(id);
        discountRepository.save(discount);
        return discount;
    }

    @Transactional
    public void deleteDiscount(Long id) {
        boolean exists = discountRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Discount with id " + id + " does not exist");
        }
        discountRepository.deleteById(id);
    }
}
