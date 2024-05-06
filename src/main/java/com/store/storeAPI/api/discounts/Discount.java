package com.store.storeAPI.api.discounts;

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
@Table(name = "discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "discount_id")
    private long id;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "price")
    private Integer price;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "required_discount_id1")
    private Long  requiredDiscountId1;

    @Column(name = "required_discount_id2")
    private Long  requiredDiscountId2;

    @Column(name = "required_discount_id3")
    private Long  requiredDiscountId3;

    @Column(name = "required_discount_id4")
    private Long  requiredDiscountId4;

}
