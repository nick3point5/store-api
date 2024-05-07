package com.store.storeAPI.api.combos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "combos")
public class Combo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "combo_id")
    private long id;

    @Column(name = "amount")
    private int amount;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "discount_id")
    private long discountId;


    public Combo(int amount, long productId, long discountId) {
        this.amount = amount;
        this.productId = productId;
        this.discountId = discountId;
    }

}