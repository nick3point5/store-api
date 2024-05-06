package com.store.storeAPI.api.combos;

import com.store.storeAPI.api.products.Product;
import jakarta.persistence.*;
import lombok.*;

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

    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    @OneToOne
    private Product product;

    @Column(name = "discount_id")
    private long discountId;

    public Combo(int amount, long productId, long discountId) {
        this.amount = amount;
        this.productId = productId;
        this.discountId = discountId;
    }

}