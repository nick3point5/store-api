package com.store.storeAPI.api.products;

import com.store.storeAPI.api.discounts.Discount;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    @OneToMany
    private List<Discount> discounts;

    public Product(String code, int price) {
        this.code = code;
        this.price = price;
    }
}
