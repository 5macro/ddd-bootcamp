package com.ecommerce.order.order.command;

import com.ecommerce.order.common.ddd.Command;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public class OrderItemCommand implements Command {
    @NotBlank(message = "产品ID不能为空")
    private UUID productId;

    @Min(value = 1, message = "产品数量必须大于0")
    private int count;

    @NotNull(message = "产品单价不能为空")
    private BigDecimal itemPrice;

    @JsonCreator
    public OrderItemCommand(@JsonProperty("productId") UUID productId,
                            @JsonProperty("count") int count,
                            @JsonProperty("itemPrice") BigDecimal itemPrice) {
        this.productId = productId;
        this.count = count;
        this.itemPrice = itemPrice;
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
