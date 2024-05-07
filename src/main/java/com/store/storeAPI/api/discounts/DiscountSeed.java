package com.store.storeAPI.api.discounts;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class DiscountSeed {
    @Bean
    @Order(2)
    CommandLineRunner discountCommandLineRunner(DiscountService discountService) {
        return args -> {
            discountService.addDiscount(new Discount(4, 700, Discount.Type.PERCENT, 1L));
            discountService.addDiscount(new Discount(6, 600, Discount.Type.PERCENT, 3L));
            discountService.addDiscount(new Discount(5, 1100, Discount.Type.BULK, 2L));
            discountService.addDiscount(new Discount(10, 1000, Discount.Type.BULK, 2L));
            discountService.addDiscount(new Discount(1, 0, Discount.Type.COMBO, 4L));
        };
    }
}
