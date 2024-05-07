package com.store.storeAPI.api.combos;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class ComboSeed {
	@Bean
	@Order(3)
	CommandLineRunner comboCommandLineRunner(ComboService comboService) {
		return args -> {
			comboService.addCombo(new Combo(1, 1L, 5L));
			comboService.addCombo(new Combo(1, 2L, 5L));
		};
	}
}
