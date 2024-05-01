package com.store.storeAPI.receipts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("receipts")
public class ReceiptController {
    @Autowired
    ReceiptService receiptService;

    @GetMapping
    public ResponseEntity<List<Receipt>> getAllReceipts() {
        return ResponseEntity.ok(receiptService.getAllReceipts());
    }

    @GetMapping("{id}")
    public ResponseEntity<Receipt> getReceiptById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(receiptService.getReceiptById(id));
    }

    @PostMapping
    public ResponseEntity<Receipt> addReceipt(@RequestBody Receipt receipt) {
        return ResponseEntity.ok(receiptService.addReceipt(receipt));
    }

    @PutMapping("{id}")
    public ResponseEntity<Receipt> updateReceipt(@PathVariable(name = "id") Long id, @RequestBody Receipt receipt) {
        return ResponseEntity.ok(receiptService.updateReceipt(id, receipt));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteReceipt(@PathVariable(name = "id") Long id) {
        receiptService.deleteReceipt(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("add")
    public ResponseEntity<Receipt> addProduct(@RequestBody ScanRequest scanRequest) {
        return ResponseEntity.ok(receiptService.addProduct(scanRequest.cartId, scanRequest.productId, scanRequest.quantity));
    }

    @PostMapping("remove")
    public ResponseEntity<Receipt> removeProduct(@RequestBody ScanRequest scanRequest) {
        return ResponseEntity.ok(receiptService.removeProduct(scanRequest.cartId, scanRequest.productId, scanRequest.quantity));
    }

    @PostMapping("set")
    public ResponseEntity<Receipt> setQuantity(@RequestBody ScanRequest scanRequest) {
        return ResponseEntity.ok(receiptService.setQuantity(scanRequest.cartId, scanRequest.productId, scanRequest.quantity));
    }

    @GetMapping("{cartId}/total")
    public ResponseEntity<Integer> getTotalPriceByCartId(@PathVariable("cartId") Long cartId) {
        return ResponseEntity.ok(receiptService.getTotalPriceByCartId(cartId));
    }
}

