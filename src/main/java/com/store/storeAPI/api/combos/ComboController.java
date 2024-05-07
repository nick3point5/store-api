package com.store.storeAPI.api.combos;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("combos")
public class ComboController {
	@Autowired
	ComboService comboService;

	@Operation(summary = "Get all combos", description = "Fetch all combos from the database", tags = {"combo"})
	@GetMapping(produces = {"application/json"})
	public ResponseEntity<List<Combo>> getAllCombos() {
		return ResponseEntity.ok(comboService.getAllCombos());
	}

	@Operation(summary = "Get a combo", description = "Fetch combo from the database by its id", tags = {"combo"})
	@GetMapping(value = "{id}", produces = {"application/json"})
	public ResponseEntity<Combo> getComboById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(comboService.getComboById(id));
	}


	@Operation(summary = "Add a new combo", description = "Add a new combo to the database", tags = {"combo"})
	@PostMapping(produces = {"application/json"})
	public ResponseEntity<Combo> addCombo(@RequestBody Combo combo) {
		return ResponseEntity.ok(comboService.addCombo(combo));
	}


	@Operation(summary = "Update a combo", description = "Update a combo in the database", tags = {"combo"})
	@PutMapping(value = "{id}", produces = {"application/json"})
	public ResponseEntity<Combo> updateCombo(@PathVariable(name = "id") Long id, @RequestBody Combo combo) {
		return ResponseEntity.ok(comboService.updateCombo(id, combo));
	}

	@Operation(summary = "Delete a combo", description = "Delete a combo from the database", tags = {"combo"})
	@DeleteMapping(value = "{id}", produces = {"application/json"})
	public ResponseEntity<Void> deleteCombo(@PathVariable(name = "id") Long id) {
		comboService.deleteCombo(id);
		return ResponseEntity.ok().build();
	}
}
