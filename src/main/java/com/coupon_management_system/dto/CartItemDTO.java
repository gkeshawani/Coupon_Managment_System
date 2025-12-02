package com.coupon_management_system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CartItemDTO {
    private Long productId;

    private Integer quantity;
    private Long unitPrice;

    @JsonProperty("price")
    public void setPrice(Long price) {
        this.unitPrice = price;
    }
}
