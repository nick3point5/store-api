package com.store.storeAPI.api.products;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
	private final ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productsRepository) {
		this.productRepository = productsRepository;
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getProductById(Long id) {
		return productRepository
				.findById(id)
				.orElseThrow(
						() -> new IllegalStateException("Product with id " + id + " does not exist")
				);
	}

	public List<Product> getProductByCode(String code) {
		return productRepository
				.findByCode(code);
	}

	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Transactional
	public Product updateProduct(Long id, Product product) {
		boolean exists = productRepository.existsById(id);
		if (!exists) {
			throw new IllegalStateException("Product with id " + product.getId() + " does not exist");
		}

		product.setId(id);
		productRepository.save(product);
		return product;
	}

	@Transactional
	public void deleteProduct(Long id) {
		boolean exists = productRepository.existsById(id);
		if (!exists) {
			throw new IllegalStateException("Product with id " + id + " does not exist");
		}
		productRepository.deleteById(id);
	}


}
