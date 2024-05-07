package com.store.storeAPI.api.products;

import com.store.storeAPI.api.discounts.Discount;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
