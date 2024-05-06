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
            comboService.addCombo(new Combo(4, 1L, 4L ));

        };
    }
}
