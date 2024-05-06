package com.store.storeAPI.api.combos;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComboService {
    private final ComboRepository comboRepository;

    @Autowired
    public ComboService(ComboRepository combosRepository) {
        this.comboRepository = combosRepository;
    }

    public List<Combo> getAllCombos() {
        return comboRepository.findAll();
    }

    public Combo getComboById(Long id) {
        return comboRepository
                .findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("Combo with id " + id + " does not exist")
                );
    }
    public Combo addCombo(Combo combo) {
        return comboRepository.save(combo);
    }
    @Transactional
    public Combo updateCombo(Long id, Combo combo) {
        boolean exists = comboRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Combo with id " + combo.getId() + " does not exist");
        }

        combo.setId(id);
        comboRepository.save(combo);
        return combo;
    }

    @Transactional
    public void deleteCombo(Long id) {
        boolean exists = comboRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Combo with id " + id + " does not exist");
        }
        comboRepository.deleteById(id);
    }
}
