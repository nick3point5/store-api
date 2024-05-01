package com.store.storeAPI.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
	@Autowired
	ProductService productService;

	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@GetMapping("{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(productService.getProductById(id));
	}
	@GetMapping(params = "code")
	@ResponseBody
	public ResponseEntity<Product> getProductByCode(@RequestParam(name = "code") String code) {
		return ResponseEntity.ok(productService.getProductByCode(code));
	}

	@PostMapping
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		return ResponseEntity.ok(productService.addProduct(product));
	}


	@PutMapping("{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable(name = "id") Long id, @RequestBody Product product) {
		return ResponseEntity.ok(productService.updateProduct(id, product));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.ok().build();
	}


}
