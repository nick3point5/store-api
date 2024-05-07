package com.store.storeAPI.api.carts;

import com.store.storeAPI.api.discounts.Discount;
import com.store.storeAPI.api.products.Product;
import com.store.storeAPI.api.receipts.Receipt;
import com.store.storeAPI.api.receipts.ReceiptRepository;
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

            int minPrice = product.getPrice() * receipt.getQuantity();

            for (Discount discount : product.getDiscounts()) {
                int discountPrice = Discount.getDiscountPrice(discount, receipt, receipts);
                if (discountPrice < minPrice) {
                    minPrice = discountPrice;
                }
            }

            totalPrice += minPrice;
        }

        return new CartTotal(totalPrice, receipts);
    }
}
