package com.store.storeAPI.api.receipts;

import com.store.storeAPI.api.products.Product;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "receipts")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "receipt_id")
    private long id;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "cart_id")
    private long cartId;

    @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
    @ManyToOne
    private Product product;

    public Receipt(long productId, int quantity, long cartId) {
        this.productId = productId;
        this.quantity = quantity;
        this.cartId = cartId;
    }

}
