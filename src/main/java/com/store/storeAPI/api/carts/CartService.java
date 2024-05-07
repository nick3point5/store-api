package com.store.storeAPI.api.carts;

import com.store.storeAPI.api.combos.Combo;
import com.store.storeAPI.api.discounts.Discount;
import com.store.storeAPI.api.products.Product;
import com.store.storeAPI.api.receipts.Receipt;
import com.store.storeAPI.api.receipts.ReceiptRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
                 int discountPrice = getDiscountPrice(discount, receipt, receipts);
                 if(discountPrice < minPrice) {
                     minPrice = discountPrice;
                 }
            }

            totalPrice += minPrice;
        }

        return new CartTotal(totalPrice, receipts);
    }

    public int getDiscountPrice(Discount discount, Receipt receipt, List<Receipt> receipts) {
        switch (discount.getType()) {
            case PERCENT:
                return getPercentPrice(discount, receipt);
            case BULK:
                return getBulkPrice(discount, receipt);
            case COMBO:
                return getComboPrice(discount, receipt, receipts);
            default:
                return 0;
        }
    }

    public int getPercentPrice(Discount discount, Receipt receipt) {
        Product product = receipt.getProduct();

        int price = 0;

        int discountQuantity = receipt.getQuantity() / discount.getAmount();
        int fullPriceQuantity = receipt.getQuantity() - discountQuantity * discount.getAmount();
        price += fullPriceQuantity * product.getPrice() + discountQuantity * discount.getPrice();

        return price;
    }

    public int getBulkPrice(Discount discount, Receipt receipt) {
        if (receipt.getQuantity() >= discount.getAmount()) {
            return discount.getPrice() * receipt.getQuantity();
        }
        return receipt.getProduct().getPrice() * receipt.getQuantity();
    }

    public int getComboPrice(Discount discount, Receipt discountReceipt, List<Receipt> receipts) {
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


        System.out.println("combos");
        System.out.println(comboCountMap.size());
        int minCombo = Integer.MAX_VALUE;
        for (Long productId : comboCountMap.keySet()) {
            int comboCount = comboCountMap.get(productId);
            System.out.println(productId);
            System.out.println(comboCount);
            if(comboCount == 0) {
                return discountReceipt.getProduct().getPrice() * discountReceipt.getQuantity();
            }

            if(comboCount < minCombo) {
                minCombo = comboCount;
            }
        }


        return discount.getPrice() * (discountReceipt.getQuantity() - minCombo);
    }
}
