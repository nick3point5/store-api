package com.store.storeAPI.api.discounts;


import com.store.storeAPI.api.discounts.Discount;
import com.store.storeAPI.api.discounts.DiscountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscountSeed {
    @Bean
    CommandLineRunner discountCommandLineRunner(DiscountService discountService) {
        return args -> {
            discountService.addDiscount(new Discount(4, 700, Discount.Type.PERCENT, 1L));
            discountService.addDiscount(new Discount(6, 600, Discount.Type.PERCENT, 3L));
            discountService.addDiscount(new Discount(5, 1100, Discount.Type.BULK, 2L));
            discountService.addDiscount(new Discount(10, 1000, Discount.Type.BULK, 2L));

        };
    }
}
