package com.store.storeAPI.api.carts;

import com.store.storeAPI.api.receipts.Receipt;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
