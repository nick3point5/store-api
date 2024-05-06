package com.store.storeAPI.api.combos;


import com.store.storeAPI.api.combos.Combo;
import com.store.storeAPI.api.combos.ComboService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComboSeed {
    @Bean
    CommandLineRunner comboCommandLineRunner(ComboService comboService) {
        return args -> {
            comboService.addCombo(new Combo(1, 1L, 5L ));
            comboService.addCombo(new Combo(1, 2L, 5L ));

        };
    }
}
