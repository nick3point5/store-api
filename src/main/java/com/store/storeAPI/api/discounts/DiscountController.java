package com.store.storeAPI.api.discounts;

import com.store.storeAPI.api.carts.CartService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("discounts")
public class DiscountController {
    @Autowired
    DiscountService discountService;
    @Operation(summary = "Get all discounts", description = "Fetch all discounts from the database", tags = {"discount"})
    @GetMapping(produces = {"application/json"})
    public ResponseEntity<List<Discount>> getAllDiscounts() {
        return ResponseEntity.ok(discountService.getAllDiscounts());
    }

    @Operation(summary = "Get a discount", description = "Fetch discount from the database by its id", tags = {"discount"})
    @GetMapping(value = "{id}", produces = {"application/json"})
    public ResponseEntity<Discount> getDiscountById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(discountService.getDiscountById(id));
    }


    @Operation(summary = "Add a new discount", description = "Add a new discount to the database", tags = {"discount"})
    @PostMapping(produces = {"application/json"})
    public ResponseEntity<Discount> addDiscount(@RequestBody Discount discount) {
        return ResponseEntity.ok(discountService.addDiscount(discount));
    }


    @Operation(summary = "Update a discount", description = "Update a discount in the database", tags = {"discount"})
    @PutMapping(value = "{id}", produces = {"application/json"})
    public ResponseEntity<Discount> updateDiscount(@PathVariable(name = "id") Long id, @RequestBody Discount discount) {
        return ResponseEntity.ok(discountService.updateDiscount(id, discount));
    }

    @Operation(summary = "Delete a discount", description = "Delete a discount from the database", tags = {"discount"})
    @DeleteMapping(value = "{id}", produces = {"application/json"})
    public ResponseEntity<Void> deleteDiscount(@PathVariable(name = "id") Long id) {
        discountService.deleteDiscount(id);
        return ResponseEntity.ok().build();
    }
}
