package com.store.storeAPI.products;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {
    @Bean
    CommandLineRunner productCommandLineRunner(ProductService productService) {
        return args -> {
            productService.addProduct(new Product("A", 200, 4, 700));
            productService.addProduct(new Product("B", 1200));
            productService.addProduct(new Product("C", 125, 6, 600));
            productService.addProduct(new Product("D", 15));
        };
    }
}
