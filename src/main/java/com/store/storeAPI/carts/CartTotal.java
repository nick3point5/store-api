package com.store.storeAPI.carts;

import com.store.storeAPI.receipts.Receipt;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
class CartTotal {
    private int totalPrice;
    private List<Receipt> receipts;
}
