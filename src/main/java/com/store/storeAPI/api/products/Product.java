package com.store.storeAPI.api.products;

import com.store.storeAPI.api.discounts.Discount;
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

    @Column(name = "code", columnDefinition = "VARCHAR(127)")
    private String code;

    @Column(name = "price")
    private int price;

    @JoinColumn(name = "discount_id", referencedColumnName = "discount_id", insertable = false, updatable = false)
    @ManyToOne
    private Discount discount;

    public Product(String code, int price) {
        this.code = code;
        this.price = price;
    }
}
