package com.coupon_management_system.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatedCartItemDTO {
    private Long productId;
    private Integer quantity;
    private Long unitPrice;
    private Long totalDiscount;
}
