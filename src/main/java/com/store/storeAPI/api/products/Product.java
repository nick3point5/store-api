package com.store.storeAPI.api.products;

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

    @Column(name = "discount_amount")
    private Integer discountAmount;

    @Column(name = "discount_price")
    private Integer discountPrice;

    public Product(String code, int price) {
        this.code = code;
        this.price = price;
    }

    public Product(String code, int price, int discountAmount, int discountPrice) {
        this.code = code;
        this.price = price;
        this.discountAmount = discountAmount;
        this.discountPrice = discountPrice;
    }
}
