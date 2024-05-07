package com.store.storeAPI.api.receipts;


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

