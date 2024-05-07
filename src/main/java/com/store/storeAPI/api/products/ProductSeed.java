package com.store.storeAPI.api.products;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class ProductSeed {
    @Bean
    @Order(1)
    CommandLineRunner productCommandLineRunner(ProductService productService) {
        return args -> {
            productService.addProduct(new Product("A", 200));
            productService.addProduct(new Product("B", 1200));
            productService.addProduct(new Product("C", 125));
            productService.addProduct(new Product("D", 15));
        };
    }
}
