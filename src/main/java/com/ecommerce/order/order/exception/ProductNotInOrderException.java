package com.ecommerce.order.order.exception;

import com.ecommerce.order.common.exception.AppException;
import com.ecommerce.order.order.model.OrderId;

import java.util.UUID;

import static com.ecommerce.order.common.exception.ErrorCode.PRODUCT_NOT_IN_ORDER;
import static com.google.common.collect.ImmutableMap.of;

public class ProductNotInOrderException extends AppException {
    public ProductNotInOrderException(UUID productId, OrderId orderId) {
        super(PRODUCT_NOT_IN_ORDER, of("productId", productId.toString(),
                "orderId", orderId.toString()));
    }
}
