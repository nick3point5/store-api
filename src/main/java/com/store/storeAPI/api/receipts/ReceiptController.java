package com.store.storeAPI.api.receipts;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("receipts")
public class ReceiptController {
    @Autowired
    ReceiptService receiptService;

    @Operation(summary = "Get all receipts", description = "Fetch all receipts from the database", tags = {"receipt"})
    @GetMapping(produces = {"application/json"})
    public ResponseEntity<List<Receipt>> getAllReceipts() {
        return ResponseEntity.ok(receiptService.getAllReceipts());
    }

    @Operation(summary = "Get a receipt", description = "Fetch receipts from the database by its id", tags = {"receipt"})
    @GetMapping(value = "{id}", produces = {"application/json"})
    public ResponseEntity<Receipt> getReceiptById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(receiptService.getReceiptById(id));
    }

    @Operation(summary = "Add a new receipt", description = "Add a new receipt to the database", tags = {"receipt"})
    @PostMapping(produces = {"application/json"})
    public ResponseEntity<Receipt> addReceipt(@RequestBody Receipt receipt) {
        return ResponseEntity.ok(receiptService.addReceipt(receipt));
    }

    @Operation(summary = "Update a receipt", description = "Updates a receipt in the database", tags = {"receipt"})
    @PutMapping(value = "{id}", produces = {"application/json"})
    public ResponseEntity<Receipt> updateReceipt(@PathVariable(name = "id") Long id, @RequestBody Receipt receipt) {
        return ResponseEntity.ok(receiptService.updateReceipt(id, receipt));
    }

    @Operation(summary = "Delete a receipt", description = "Deletes a receipt from the database", tags = {"receipt"})
    @DeleteMapping(value = "{id}", produces = {"application/json"})
    public ResponseEntity<Void> deleteReceipt(@PathVariable(name = "id") Long id) {
        receiptService.deleteReceipt(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Add product to cart", description = "Increment and update the product quantity in " +
            "the receipt corresponding to the cart in the database. If the product is not in the cart, a new receipt will be " +
            "created for the cart",
            tags = {
            "receipt"})
    @PostMapping(value = "add", produces = {"application/json"})
    public ResponseEntity<Receipt> addProduct(@RequestBody ScanRequest scanRequest) {
        return ResponseEntity.ok(receiptService.addProduct(scanRequest.cartId, scanRequest.productId, scanRequest.quantity));
    }

    @Operation(summary = "Remove product from cart", description = "Decrement and update the product quantity in the receipt corresponding to the cart in the database. If the quantity is 0 or less, the receipt will " +
            "be deleted for the cart", tags = {
            "receipt"})
    @PostMapping(value = "remove", produces = {"application/json"})
    public ResponseEntity<Receipt> removeProduct(@RequestBody ScanRequest scanRequest) {
        return ResponseEntity.ok(receiptService.removeProduct(scanRequest.cartId, scanRequest.productId, scanRequest.quantity));
    }

    @Operation(summary = "Set quantity of product in cart", description = "Set the product quantity in the " +
            "receipt corresponding to the cart in the database. If the quantity is 0 or less, the receipt will be delete" +
            " in the database",
            tags = {
            "receipt"})
    @PostMapping(value = "set", produces = {"application/json"})
    public ResponseEntity<Receipt> setQuantity(@RequestBody ScanRequest scanRequest) {
        return ResponseEntity.ok(receiptService.setQuantity(scanRequest.cartId, scanRequest.productId, scanRequest.quantity));
    }
}

