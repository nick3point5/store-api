package com.store.storeAPI.api.discounts;

import com.store.storeAPI.api.combos.Combo;
import com.store.storeAPI.api.products.Product;
import com.store.storeAPI.api.receipts.Receipt;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
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

    public static int getDiscountPrice(Discount discount, Receipt receipt, List<Receipt> receipts) {
        switch (discount.getType()) {
            case PERCENT:
                return Discount.getPercentPrice(discount, receipt);
            case BULK:
                return Discount.getBulkPrice(discount, receipt);
            case COMBO:
                return Discount.getComboPrice(discount, receipt, receipts);
            default:
                return 0;
        }
    }

    private static int getPercentPrice(Discount discount, Receipt receipt) {
        Product product = receipt.getProduct();

        int price = 0;

        int discountQuantity = receipt.getQuantity() / discount.getAmount();
        int fullPriceQuantity = receipt.getQuantity() - discountQuantity * discount.getAmount();
        price += fullPriceQuantity * product.getPrice() + discountQuantity * discount.getPrice();

        return price;
    }

    private static int getBulkPrice(Discount discount, Receipt receipt) {
        if (receipt.getQuantity() >= discount.getAmount()) {
            return discount.getPrice() * receipt.getQuantity();
        }
        return receipt.getProduct().getPrice() * receipt.getQuantity();
    }

    private static int getComboPrice(Discount discount, Receipt discountReceipt, List<Receipt> receipts) {
        List<Combo> combos = discount.getCombos();


        HashMap<Long, Integer> comboMap = new HashMap<>();
        HashMap<Long, Integer> comboCountMap = new HashMap<>();

        for (Combo combo : combos) {

            comboMap.put(combo.getProductId(), combo.getAmount());
            comboCountMap.put(combo.getProductId(), 0);
        }

        HashMap<Long, Integer> productQuantityMap = new HashMap<>();
        for (Receipt receipt : receipts) {
            productQuantityMap.put(receipt.getProduct().getId(), receipt.getQuantity());
        }


        for (Long productId : comboMap.keySet()) {
            if (productQuantityMap.containsKey(productId)) {
                int quantity = productQuantityMap.get(productId);

                int amount = comboMap.get(productId);

                int comboCount = quantity / amount;

                comboCountMap.put(productId, comboCount);

            }
        }


        int minCombo = Integer.MAX_VALUE;
        for (Long productId : comboCountMap.keySet()) {
            int comboCount = comboCountMap.get(productId);

            if (comboCount == 0) {
                return discountReceipt.getProduct().getPrice() * discountReceipt.getQuantity();
            }

            if (comboCount < minCombo) {
                minCombo = comboCount;
            }
        }


        return discount.getPrice() * (discountReceipt.getQuantity() - minCombo);
    }
}