package com.store.storeAPI.api.discounts;

import com.store.storeAPI.api.combos.Combo;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "discounts")
public class Discount {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "discount_id")
	private long id;
	@Column(name = "amount")
	private int amount;
	@Column(name = "price")
	private int price;
	@Column(name = "type")
	private Type type;
	@Column(name = "product_id")
	private Long productId;
	@JoinColumn(name = "discount_id", referencedColumnName = "discount_id", insertable = false, updatable = false)
	@OneToMany
	private List<Combo> combos;

	public Discount(int amount, int price, Type type, Long productId) {
		this.amount = amount;
		this.price = price;
		this.type = type;
		this.productId = productId;
	}

	@Getter
	@AllArgsConstructor
	@ToString
	public enum Type {
		PERCENT(1),
		BULK(2),
		COMBO(3);

		private final int value;
	}


}