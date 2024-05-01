package com.store.storeAPI.receipts;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
class ScanRequest {
    Long cartId;
    Long productId;
    int quantity;
}

