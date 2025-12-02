package com.coupon_management_system.dto;

import lombok.Data;

@Data
public class ProductWiseDetailsDTO {
    private Long[] eligibleProductIds;
    private String mode;
    private Long discountValue;
}
