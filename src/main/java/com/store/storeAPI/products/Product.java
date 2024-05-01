package com.store.storeAPI.products;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private long id;

	@Column(name = "code")
	private String code;

	@Column(name = "price")
	private int price;

	public Product(String code, int price) {
		this.code = code;
		this.price = price;
	}
}
