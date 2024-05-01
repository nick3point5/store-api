package com.store.storeAPI.api.carts;

import com.store.storeAPI.api.receipts.Receipt;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("carts")
public class CartController {
	@Autowired
	CartService cartService;

	@Operation(summary = "Get all carts", description = "Fetch all carts from the database", tags = {"cart"})
	@GetMapping(produces = {"application/json"})
	public ResponseEntity<List<Cart>> getAllCarts() {
		return ResponseEntity.ok(cartService.getAllCarts());
	}

	@Operation(summary = "Get a cart", description = "Fetch cart from the database by its id", tags = {"cart"})
	@GetMapping(value = "{id}", produces = {"application/json"})
	public ResponseEntity<Cart> getCartById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(cartService.getCartById(id));
	}


	@Operation(summary = "Add a new cart", description = "Add a new cart to the database", tags = {"cart"})
	@PostMapping(produces = {"application/json"})
	public ResponseEntity<Cart> addCart(@RequestBody Cart cart) {
		return ResponseEntity.ok(cartService.addCart(cart));
	}


	@Operation(summary = "Update a cart", description = "Update a cart in the database", tags = {"cart"})
	@PutMapping(value = "{id}", produces = {"application/json"})
	public ResponseEntity<Cart> updateCart(@PathVariable(name = "id") Long id, @RequestBody Cart cart) {
		return ResponseEntity.ok(cartService.updateCart(id, cart));
	}

	@Operation(summary = "Delete a cart", description = "Delete a cart from the database", tags = {"cart"})
	@DeleteMapping(value = "{id}", produces = {"application/json"})
	public ResponseEntity<Void> deleteCart(@PathVariable(name = "id") Long id) {
		cartService.deleteCart(id);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "Get all receipts for a given cart", description = "Fetch all receipts for a given cart",
			tags = {"cart"})
	@GetMapping(value = "receipts/{cartId}", produces = {"application/json"})
	public ResponseEntity<List<Receipt>> getReceiptsByCartId(@PathVariable("cartId") Long cartId) {
		return ResponseEntity.ok(cartService.getReceiptsByCartId(cartId));
	}

	@Operation(summary = "Return total price of a cart given and its receipts", description = "Fetch all receipts " +
			"for a given cart and calculates the total price of the cart including discounts", tags = {"cart"})

	@GetMapping(value = "total/{cartId}", produces = {"application/json"})
	public ResponseEntity<CartTotal> getTotalPriceByCartId(@PathVariable("cartId") Long cartId) {
		return ResponseEntity.ok(cartService.getTotalPriceByCartId(cartId));
	}
}
