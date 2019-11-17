package com.ecommerce.order.order.model;

import com.ecommerce.order.common.ddd.Entity;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItem implements Entity {
    private UUID productId;
    private int count;
    private BigDecimal itemPrice;

    private OrderItem() {
    }

    private OrderItem(UUID productId, int count, BigDecimal itemPrice) {
        this.productId = productId;
        this.count = count;
        this.itemPrice = itemPrice;
    }

    public static OrderItem create(UUID productId, int count, BigDecimal itemPrice) {
        return new OrderItem(productId, count, itemPrice);
    }

    BigDecimal totalPrice() {
        return itemPrice.multiply(BigDecimal.valueOf(count));
    }

    public void updateCount(int count) {
        this.count = count;
    }

    public UUID getProductId() {
        return productId;
    }

    public int getCount() {
        return count;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }
}
