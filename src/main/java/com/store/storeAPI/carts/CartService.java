package com.store.storeAPI.carts;

import com.store.storeAPI.products.Product;
import com.store.storeAPI.receipts.Receipt;
import com.store.storeAPI.receipts.ReceiptRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ReceiptRepository receiptRepository;

    @Autowired
    public CartService(CartRepository cartsRepository, ReceiptRepository receiptRepository) {
        this.cartRepository = cartsRepository;
        this.receiptRepository = receiptRepository;
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getCartById(Long id) {
        return cartRepository
                .findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("Cart with id " + id + " does not exist")
                );
    }

    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart updateCart(Long id, Cart cart) {
        boolean exists = cartRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Cart with id " + cart.getId() + " does not exist");
        }

        cart.setId(id);
        cartRepository.save(cart);
        return cart;
    }

    @Transactional
    public void deleteCart(Long id) {
        boolean exists = cartRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Cart with id " + id + " does not exist");
        }
        cartRepository.deleteById(id);
    }

    public List<Receipt> getReceiptsByCartId(long cartId) {
        return receiptRepository.findByCartId(cartId);
    }

    public CartTotal getTotalPriceByCartId(Long cartId) {
        List<Receipt> receipts = receiptRepository.findByCartId(cartId);

        int totalPrice = 0;

        for (Receipt receipt : receipts) {
            Product product = receipt.getProduct();
            if (product.getDiscountAmount() == null || product.getDiscountPrice() == null) {
                totalPrice += receipt.getQuantity() * product.getPrice();
            } else {
                int discountQuantity = Math.floorDiv(receipt.getQuantity(), product.getDiscountAmount());
                int fullPriceQuantity = receipt.getQuantity() - discountQuantity * product.getDiscountAmount();
                System.out.println(fullPriceQuantity);
                totalPrice += fullPriceQuantity * product.getPrice() + discountQuantity * product.getDiscountPrice();

            }
        }

        return new CartTotal(totalPrice, receipts);
    }
}
