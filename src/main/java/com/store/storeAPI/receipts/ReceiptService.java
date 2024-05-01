package com.store.storeAPI.receipts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {
    private final ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    public Receipt getReceiptById(long id) {
        return receiptRepository
                .findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("Receipt with id " + id + " does not exist")
                );
    }

    public Receipt addReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    public Receipt updateReceipt(long id, Receipt receipt) {
        if (!receiptRepository.existsById(id)) {
            throw new IllegalStateException("Receipt with id " + id + " does not exist");
        }

        receipt.setId(id);
        return receiptRepository.save(receipt);
    }

    public void deleteReceipt(long id) {
        if (!receiptRepository.existsById(id)) {
            throw new IllegalStateException("Receipt with id " + id + " does not exist");
        }

        receiptRepository.deleteById(id);
    }

    public Receipt addProduct(long cartId, long productId, int quantity) {
        Receipt receipt = receiptRepository
                .findByCartIdAndProductId(cartId, productId)
                .orElse(new Receipt(productId, 0, cartId));

		receipt.setQuantity(receipt.getQuantity()+quantity);

        return receiptRepository.save(receipt);
    }

    public Receipt removeProduct(long cartId, long productId, int quantity) {
        Receipt receipt = receiptRepository
                .findByCartIdAndProductId(cartId, productId)
                .orElseThrow(
                        () -> new IllegalStateException("Product with id " + productId + " does not exist in cart with id " + cartId)
                );

        receipt.setQuantity(receipt.getQuantity() - quantity);

        if (receipt.getQuantity() <= 0) {
            receiptRepository.deleteById(receipt.getId());
        }

        return receiptRepository.save(receipt);
    }

    public Receipt setQuantity(long cartId, long productId, int quantity) {
        Receipt receipt = receiptRepository
                .findByCartIdAndProductId(cartId, productId)
                .orElseThrow(
                        () -> new IllegalStateException("Product with id " + productId + " does not exist in cart " + cartId)
                );

        if (quantity < 0) {
            throw new IllegalStateException("Quantity cannot be less than 0");
        }

        if (quantity == 0) {
            receiptRepository.deleteById(receipt.getId());
            return null;
        }

        receipt.setQuantity(quantity);

        return receiptRepository.save(receipt);
    }

    public int getTotalPriceByCartId(long cartId) {
        List<Receipt> receipts = receiptRepository.findByCartId(cartId);

        int totalPrice = 0;

        for (Receipt receipt : receipts) {
            totalPrice += receipt.getQuantity() * receipt.getProduct().getPrice();
        }

        return totalPrice;
    }
}
