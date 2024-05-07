package com.store.storeAPI.api.products;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    ProductService productService;

    @Operation(summary = "Get all products", description = "Fetch all products from the database", tags = {"product"})
    @GetMapping(produces = {"application/json"})
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(name = "code", required = false) String code) {
        if (code != null && !code.isEmpty()) {
            return ResponseEntity.ok(productService.getProductByCode(code));
        }
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(summary = "Get a product", description = "Fetch product from the database by its id", tags = {"product"})
    @GetMapping(value = "{id}", produces = {"application/json"})
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @Operation(summary = "Add a new product", description = "Add a new product to the database", tags = {"product"})
    @PostMapping(produces = {"application/json"})
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addProduct(product));
    }


    @Operation(summary = "Update a product", description = "Updates a product in the database", tags = {"product"})
    @PutMapping(value = "{id}", produces = {"application/json"})
    public ResponseEntity<Product> updateProduct(@PathVariable(name = "id") Long id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @Operation(summary = "Delete a product", description = "Deletes a product from the database", tags = {"product"})
    @DeleteMapping(value = "{id}", produces = {"application/json"})
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
