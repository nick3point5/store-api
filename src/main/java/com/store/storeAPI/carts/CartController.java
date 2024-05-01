package com.store.storeAPI.carts;

import com.store.storeAPI.carts.Cart;
import com.store.storeAPI.carts.CartService;
import com.store.storeAPI.receipts.Receipt;
import com.store.storeAPI.receipts.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/carts")
public class CartController {
	@Autowired
	CartService cartService;

	@GetMapping
	public ResponseEntity<List<Cart>> getAllCarts() {
		return ResponseEntity.ok(cartService.getAllCarts());
	}

	@GetMapping("{id}")
	public ResponseEntity<Cart> getCartById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(cartService.getCartById(id));
	}

	@GetMapping("total/{cartId}")
	public ResponseEntity<Integer> getTotalPriceByCartId(@PathVariable("cartId") Long cartId) {
		return ResponseEntity.ok(cartService.getTotalPriceByCartId(cartId));
	}

	@PostMapping
	public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
		return ResponseEntity.ok(cartService.addCart(cart));
	}


	@PutMapping("{id}")
	public ResponseEntity<Cart> updateCart(@PathVariable(name = "id") Long id, @RequestBody Cart cart) {
		return ResponseEntity.ok(cartService.updateCart(id, cart));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteCart(@PathVariable(name = "id") Long id) {
		cartService.deleteCart(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("receipts/{cartId}")
	public ResponseEntity<List<Receipt>> getReceiptsByCartId(@PathVariable("cartId") Long cartId) {
		return ResponseEntity.ok(cartService.getReceiptsByCartId(cartId));
	}
}
